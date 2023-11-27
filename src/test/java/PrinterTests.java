import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import src.Entity.Product;
import src.Printer;

import java.time.LocalDate;

import static org.mockito.Mockito.*;




public class PrinterTests {


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPrintProductDetails(){
        Printer printer = new Printer();
        Product product = Mockito.mock(Product.class);
        when(product.getProduct_id()).thenReturn(1);
        when(product.getName()).thenReturn("Test product");
        when(product.getQuantity()).thenReturn(10);
        when(product.getPrice()).thenReturn(20.4);
        when(product.getColor()).thenReturn("Red");
        when(product.getExpires_in()).thenReturn(LocalDate.parse("2023-12-31"));

        printer.printProductDetails(product);

        verify(product, times(1)).getProduct_id();
        verify(product, times(1)).getName();
        verify(product,times(1)).getQuantity();
        verify(product, times(1)).getPrice();
        verify(product,times(1)).getColor();
        verify(product,times(1)).getExpires_in();
    }

}
