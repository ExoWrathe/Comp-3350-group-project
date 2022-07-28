package comp3350.ims.presentation;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.DataInteraction;
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
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import comp3350.ims.R;
import comp3350.ims.application.Main;
import comp3350.ims.application.Services;
import comp3350.ims.business.AccessInventory;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InventoryTest {

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
    public void testInventory(){
        ViewInteraction button = onView(allOf(withId(R.id.buttonManager), withText("Manager"),
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
                        isDisplayed()));
        editText.perform(replaceText("pantry"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnCreateCategory),
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
                allOf(withId(R.id.btnCreateLocation),
                        isDisplayed()));
        button3.perform(click());

        pressBack();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonItems), withText("Create New Item"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.itemNameInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("oreo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.itemPriceInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.itemQuantityInput),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                11),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("12"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.itemCreateBtn),
                        isDisplayed()));
        appCompatButton4.perform(click(),closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttonInventory), withText("View Inventory"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatButton5.perform(click());


        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.Edit), withText("Edit"),
                        childAtPosition(
                                withParent(withId(R.id.activeInventoryList)),
                                3),
                        isDisplayed()));
        appCompatButton8.perform(click());

        pressBack();

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.itemNameInputs), withText("oreo"),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText3.check(matches(withText("oreo")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.itemPriceInput), withText("3.0"),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText4.check(matches(withText("3.0")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.itemPriceInput), withText("3.0"),
                        withParent(allOf(withId(R.id.gridLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        editText5.check(matches(withText("3.0")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("pantry"),
                        withParent(allOf(withId(R.id.spinnerCategory),
                                withParent(withId(R.id.gridLayout)))),
                        isDisplayed()));
        textView3.check(matches(withText("pantry")));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("oreo"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.search_src_text), withText("oreo"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        editText6.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.itemName), withText("Name: oreo"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("Name: oreo")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.itemPrice), withText("Price: $3.0"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView5.check(matches(withText("Price: $3.0")));



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
