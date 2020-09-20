import org.junit.Assert;
import org.junit.Test;

public class PolynomialTest {

    @Test
    public void newPolynomialShouldFullPositiveElements() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Assert.assertEquals("1X^2 + 2X + 3", a.toString());
    }

    @Test
    public void newPolynomialShouldFullNegativeElements() {
        Polynomial a = new Polynomial(new int[]{-1, -2, -3});
        Assert.assertEquals("-1X^2 - 2X - 3", a.toString());
    }

    @Test
    public void newPolynomialShouldPositiveAndNegativeElements() {
        Polynomial a = new Polynomial(new int[]{-1, 2, -3, 4});
        Assert.assertEquals("-1X^3 + 2X^2 - 3X + 4", a.toString());
    }

    @Test
    public void newPolynomialNotShouldZero() {
        Polynomial a = new Polynomial(new int[]{-1, 0, -3, 4});
        Assert.assertEquals("-1X^3 - 3X + 4", a.toString());

        Polynomial b = new Polynomial(new int[]{-1, 0, -3, 4, 0, 6, 7, 0});
        Assert.assertEquals("-1X^7 - 3X^5 + 4X^4 + 6X^2 + 7X", b.toString());
    }

    @Test
    public void sumPolynomials() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Polynomial b = new Polynomial(new int[]{4, 5, 6, 7});

        //Проверка на перестановку слагаемых
        Polynomial c = Polynomial.sum(a, b);
        Polynomial d = Polynomial.sum(b, a);

        //От перестановки слогаемых сумма не должна меняться
        Assert.assertEquals("4X^3 + 6X^2 + 8X + 10", c.toString());
        Assert.assertEquals("4X^3 + 6X^2 + 8X + 10", d.toString());
    }

    @Test
    public void subPolynomials() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Polynomial b = new Polynomial(new int[]{4, 5, 6, 7});

        Polynomial c = Polynomial.sub(a, b); // a - b
        Polynomial d = Polynomial.sub(b, a); // b - a

        Assert.assertEquals("-4X^3 - 4X^2 - 4X - 4", c.toString());
        Assert.assertEquals("4X^3 + 4X^2 + 4X + 4", d.toString());
    }

    @Test
    public void mulPolynomials() {
        Polynomial a = new Polynomial(new int[]{2, 0, -3}); //2X^2 - 3
        Polynomial b = new Polynomial(new int[]{1, 2}); //X + 2
        Polynomial c = Polynomial.mul(a, b);
        Assert.assertEquals("2X^3 + 4X^2 - 3X - 6", c.toString());

        //Проверка сокрощения подобных членов
        Polynomial a1 = new Polynomial(new int[]{1, -1}); //X - 1
        Polynomial b1 = new Polynomial(new int[]{1, 1, 1}); //X^2 + X + 1
        Polynomial c1 = Polynomial.mul(a1, b1);
        Polynomial d1 = Polynomial.mul(b1, a1);
        Assert.assertEquals("1X^3 - 1", c1.toString());
        Assert.assertEquals("1X^3 - 1", d1.toString());

        //Проверка сложения подобных членов
        Polynomial a2 = new Polynomial(new int[]{1, 1, 0, 10}); //X^3 + X^2 + 10
        Polynomial b2 = new Polynomial(new int[]{1, 2, 5}); //X^2 + 2X + 5
        Polynomial c2 = Polynomial.mul(a2, b2);
        Assert.assertEquals("1X^5 + 3X^4 + 7X^3 + 15X^2 + 20X + 50", c2.toString());
    }
}
