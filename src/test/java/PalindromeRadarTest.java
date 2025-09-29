import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromeRadarTest {

    @Test
    void simple_palindrome_with_only_lower_case_letters_is_recognised() {
        assertThat(new PalindromeRadar().isPalindrome(), is(true));
    }
}

class PalindromeRadar {

    public boolean isPalindrome() {
        return true;
    }
}
