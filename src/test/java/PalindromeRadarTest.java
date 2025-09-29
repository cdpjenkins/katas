import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromeRadarTest {

    PalindromeRadar palindromeRadar = new PalindromeRadar();

    @Test
    void simple_palindrome_with_only_lower_case_letters_is_recognised() {
        assertThat(palindromeRadar.isPalindrome("anna"), is(true));
    }

    @Test
    void non_palindrome_is_recognised_as_not_palindrome() {
        assertThat(palindromeRadar.isPalindrome("notpalindrome"), is(false));
    }

    @Test
    void ignore_non_alphanumeric_chars() {
        assertThat(palindromeRadar.isPalindrome("rad ar"), is(true));
    }
}

class PalindromeRadar {

    public boolean isPalindrome(String input) {
        String alphaNumericInput = filterAlphanumeric(input);

        return alphaNumericInput.equals(reverse(alphaNumericInput));
    }

    private static String filterAlphanumeric(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
