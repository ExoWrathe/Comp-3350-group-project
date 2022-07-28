package comp3350.ims.presentation;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.ims.R;
import comp3350.ims.application.Main;
import comp3350.ims.application.Services;
import comp3350.ims.business.AccessInventory;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ItemCreateTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();
        AccessInventory accessInventory = new AccessInventory(true);

    }

    @Test
    public void itemCreateTest() {

        ViewInteraction button = onView(
                allOf(withId(R.id.buttonManager), withText("Manager"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonCategory),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText.perform(replaceText("pantry"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnCreateCategory), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button2.perform(click());

        pressBack();


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonLocation),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText2.perform(replaceText("floor"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnCreateLocation), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button3.perform(click());

        pressBack();


        ViewInteraction button4 = onView(
                allOf(withId(R.id.buttonItems), withText("Create New Item"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonItems), withText("Create New Item"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.itemNameInput),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.itemPriceInput),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText4.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.itemQuantityInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                11),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.itemQuantityInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                11),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        pressBack();

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.itemQuantityInput),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText5.check(matches(isDisplayed()));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editTextDate),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText6.check(matches(isDisplayed()));

        ViewInteraction spinner = onView(
                allOf(withId(R.id.spinnerCategory),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction spinner2 = onView(
                allOf(withId(R.id.spinnerLocation),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        spinner2.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.itemCreateBtn),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.itemCreateBtn),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.itemNameInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Oreo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.itemPriceInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.itemQuantityInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                11),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("12"), closeSoftKeyboard());


        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.itemCreateBtn),
                        isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.buttonInventory), withText("View Inventory"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.itemName), withText("Name: Oreo"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Name: Oreo")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.itemPrice), withText("Price: $3.0"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Price: $3.0")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.categoryName), withText("Category: pantry"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Category: pantry")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.itemQuantity), withText("Quantity: 12"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("Quantity: 12")));

        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(R.id.activeInventoryList),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @After
    public void tearDown()  {
        Services.closeDataAccess();

    }
}
