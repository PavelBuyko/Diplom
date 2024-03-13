import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BunTest {
    private final String EXPECTEDNAME="testBulka";
    private final float EXPECTEDPRICE = 2000;

    @Test
    public void BunGetNameTest()
    {
        Bun bun = new Bun(EXPECTEDNAME,EXPECTEDPRICE);
        assertEquals("Названия булочек не совпадают",bun.getName(),EXPECTEDNAME);

    }
    @Test
    public void BunGetPriceTest()
    {
        Bun bun = new Bun(EXPECTEDNAME,EXPECTEDPRICE);
        assertEquals(bun.getPrice(),EXPECTEDPRICE,0.01);

    }

}
