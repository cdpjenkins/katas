import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromeRadarTest {

    @Test
    void simple_palindrome_with_only_lower_case_letters_is_recognised() {
        assertThat(new PalindromeRadar().isPalindrome("anna"), is(true));
    }

    @Test
    void non_palindrome_is_recognised_as_not_palindrome() {
        assertThat(new PalindromeRadar().isPalindrome("notpalindrome"), is(false));
    }
}

class PalindromeRadar {

    public boolean isPalindrome(String input) {
        return input.equals(reverse(input));
    }

    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
