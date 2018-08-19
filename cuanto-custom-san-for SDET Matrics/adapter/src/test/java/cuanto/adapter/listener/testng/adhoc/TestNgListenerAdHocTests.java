package cuanto.adapter.listener.testng.adhoc;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Ad-hoc tests intended for use with TestNgListener to exercise all TestOutcome possibilities.
 * <p/>
 * Failures are expected and do not indicate a bug.
 */
public class TestNgListenerAdHocTests extends BaseTestClass
{
	@Test(groups = "1.0.0")
	public void one() {}

	@Test(groups = "reaperagent")
	public void two() {}

	@Test(groups = "1.0.0")
	public void three() {}

	@Test(groups = "reaperagent")
	public void four() {}

	@Test(groups = "Happy")
	public void testHappy1()
	{
		System.out.println("testHappy1: " + Thread.currentThread().getName());
	}

	@Test(groups = { "Happy", "Second" })
	public void testHappy2()
	{
		System.out.println("testHappy2: " + Thread.currentThread().getName());
	}

	@Test(groups = "Sad")
	public void testSad1()
	{
		System.out.println("testSad1: " + Thread.currentThread().getName());
		Assert.fail("sad1");
	}

	@Test(groups = { "Sad", "Second" })
	public void testSad2()
	{
		System.out.println("testSad2: " + Thread.currentThread().getName());
		Assert.fail("sad2");
	}

	@Test
	public void testSkip1()
	{
		System.out.println("testSkip1: " + Thread.currentThread().getName());
		throw new SkipException("skip1");
	}

	@Test
	public void testSkip2()
	{
		System.out.println("testSkip2: " + Thread.currentThread().getName());
		throw new SkipException("skip2");
	}

	@Test(
		groups = { "Happy", "Sad" },
		dataProvider = "data-provider")
	public void testDataProvider(String p1, Integer p2)
	{
		System.out.println(p1 + ":" + p2 + " - " + Thread.currentThread().getName());
		if (p1 == null || p2 == null)
			throw new IllegalArgumentException("The test method parameter may not be null.");
	}

	@DataProvider(name = "data-provider")
	private Object[][] dataProvider()
	{
		return new Object[][] {
			new Object[] { "1-param1", 12 },
			new Object[] { "2-param1", null },
			new Object[] { null, 22 },
		};
	}
}
