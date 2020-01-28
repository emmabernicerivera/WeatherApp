package me.emmarivera.weather.feature.splash.presenter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import me.emmarivera.weather.RxTestRule
import me.emmarivera.weather.feature.splash.view.SplashView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

/**
 * Tests the business logic in the SplashPresenter.
 */
@RunWith(BlockJUnit4ClassRunner::class)
class SplashPresenterTest {

  /**
   * Tells our unit tests to run all RxJava observables on the main-thread so they run in order.
   */
  @get:Rule val rxRule = RxTestRule

  /**
   * A mocked version of our view. This has no code associated with it, and it allows us to return
   * whatever we want for testing purposes.
   */
  private val mockView: SplashView = mock()

  /**
   * The class we are testing (e.g. testSubject)
   */
  private lateinit var testSubject: SplashPresenter

  /**
   * This is called before each unit test is set up. It allows us to make sure each unit test has
   * a clean instance of our test subject so we can have stable tests/state.
   */
  @Before
  fun setUp() {
    testSubject = SplashPresenterImpl(view = mockView)
  }

  @Test
  fun `WHEN onStart is called VERIFY we navigate to home`() {
    // Call the method to be tested
    testSubject.onStart()

    // Verify that we eventually call navigateToHome
    verify(mockView).navigateToHome()
  }
}