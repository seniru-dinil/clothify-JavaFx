package edu.icet.clothify.util.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.icet.clothify.dto.CartHelper;
import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.dto.Order;
import edu.icet.clothify.dto.Product;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceGenerator {

    public static void generateInvoice(String filePath, Customer customer, Order order, List<CartHelper> cartItems) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            BaseColor darkBlue = new BaseColor(0, 51, 102);
            BaseColor lightGray = new BaseColor(220, 220, 220);
            BaseColor white = BaseColor.WHITE;


            Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, darkBlue);
            Paragraph title = new Paragraph("CLOTHIFY INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);


            Font subTitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, darkBlue);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

            document.add(new Paragraph("Customer Details:", subTitleFont));
            document.add(new Paragraph(customer.getFirstName() + " " + customer.getLastName(), normalFont));
            document.add(new Paragraph("Email: " + customer.getEmail(), normalFont));
            document.add(new Paragraph("Mobile: " + customer.getMobileNumber(), normalFont));
            document.add(new Paragraph("Address: " + customer.getAddress(), normalFont));
            document.add(new Paragraph("\n"));


            document.add(new Paragraph("Order ID: " + order.getOrderId(), normalFont));
            document.add(new Paragraph("Order Date: " +
                    order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), normalFont));
            document.add(new Paragraph("\n"));


            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new float[]{3, 1, 1, 1});


            table.addCell(getStyledCell("Product Name", darkBlue, white, true));
            table.addCell(getStyledCell("Quantity", darkBlue, white, true));
            table.addCell(getStyledCell("Unit Price", darkBlue, white, true));
            table.addCell(getStyledCell("Total Price", darkBlue, white, true));


            for (CartHelper cartItem : cartItems) {
                Product product = cartItem.getProduct();
                int quantity = cartItem.getQuantity();
                double totalPrice = quantity * product.getProductPrice();

                table.addCell(getStyledCell(product.getProductName(), lightGray, BaseColor.BLACK, false));
                table.addCell(getStyledCell(String.valueOf(quantity), lightGray, BaseColor.BLACK, false));
                table.addCell(getStyledCell("LKR" + product.getProductPrice(), lightGray, BaseColor.BLACK, false));
                table.addCell(getStyledCell("LKR" + totalPrice, lightGray, BaseColor.BLACK, false));
            }

            document.add(table);


            Font totalFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, darkBlue);
            Paragraph total = new Paragraph("Grand Total: LKR " + order.getOrderTotal(), totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);


            document.add(new Paragraph("\n"));
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, darkBlue);
            Paragraph footer = new Paragraph("Thank you for shopping with Clothify!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            Font smallFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
            Paragraph disclaimer = new Paragraph("This is a computer-generated document and does not require a signature.", smallFont);
            disclaimer.setAlignment(Element.ALIGN_CENTER);
            document.add(disclaimer);

            document.close();
        } catch (Exception e) {

        }
    }

    private static PdfPCell getStyledCell(String text, BaseColor bgColor, BaseColor textColor, boolean isHeader) {
        Font font = new Font(Font.FontFamily.HELVETICA, isHeader ? 12 : 10, isHeader ? Font.BOLD : Font.NORMAL, textColor);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
