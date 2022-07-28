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
public class CreateLocationTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {

        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();
    }

    @Test
    public void createLocationTest() {
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
                allOf(withId(R.id.buttonLocation), withText("Create New Location"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonLocation), withText("Create New Location"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Location"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction listView = onView(
                allOf(withId(R.id.LocationList),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.txtLocationName),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText("Location"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnCreateLocation), withText("Create"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.btnDeleteLocation), withText("Delete"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.btnDeleteLocation), withText("Delete"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText3.perform(replaceText("Floor"), closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.btnCreateLocation), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view), withText("Floor"),
                        withParent(withParent(withId(R.id.LocationList))),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText4.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText5.perform(replaceText("Warehouse"), closeSoftKeyboard());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.btnCreateLocation), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button7.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.text_view), withText("Warehouse"),
                        withParent(withParent(withId(R.id.LocationList))),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText6.perform(click());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText7.perform(replaceText("Floor"), closeSoftKeyboard());

        ViewInteraction button8 = onView(
                allOf(withId(R.id.btnCreateLocation), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button8.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.txtLocationName), withText("Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText8.perform(replaceText("Floor"));

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.txtLocationName), withText("Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText9.perform(closeSoftKeyboard());

        ViewInteraction button9 = onView(
                allOf(withId(R.id.btnDeleteLocation), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        button9.perform(click());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.txtLocationName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText10.perform(replaceText("jsf"), closeSoftKeyboard());

        ViewInteraction button10 = onView(
                allOf(withId(R.id.btnDeleteLocation), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        button10.perform(click());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonItems),
                        isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerLocation),
                        childAtPosition(
                                allOf(withId(R.id.gridLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                9),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.text1), withText("Warehouse"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));
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
