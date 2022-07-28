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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateCategoryTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {

        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();
    }

    @Test
    public void createCategoryTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.buttonManager), withText("Manager"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonCategory), withText("Create New Category"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonCategory), withText("Create New Category"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction listView = onView(
                allOf(withId(R.id.categoryList),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.txtCategoryName),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.categoryName), withText("Name"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnCreateCategory), withText("Create"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.btnDeleteCategory), withText("Delete"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.btnDeleteCategory), withText("Delete"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText3.perform(replaceText("Pantry"), closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.btnCreateCategory), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view), withText("Pantry"),
                        withParent(withParent(withId(R.id.categoryList))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText4.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText5.perform(replaceText("Electronics"), closeSoftKeyboard());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.btnCreateCategory), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button7.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view), withText("Electronics"),
                        withParent(withParent(withId(R.id.categoryList))),
                        isDisplayed()));
        textView3.check(matches(withText("Electronics")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText6.perform(click());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText7.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText8.perform(replaceText("Electronics"), closeSoftKeyboard());

        ViewInteraction button8 = onView(
                allOf(withId(R.id.btnDeleteCategory),
                        isDisplayed()));
        button8.perform(click());


        ViewInteraction editText9 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText9.perform(click());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText10.perform(replaceText("Pantry"), closeSoftKeyboard());

        ViewInteraction button9 = onView(
                allOf(withId(R.id.btnCreateCategory), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button9.perform(click());

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.txtCategoryName), withText("Pantry"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText11.perform(click());

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.txtCategoryName), withText("Pantry"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText12.perform(replaceText(""));

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.txtCategoryName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText13.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonItems), withText("Create New Item"),
                        isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerCategory),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("Pantry"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));


        pressBack();
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
    public void tearDown(){
        Services.closeDataAccess();
    }
}
