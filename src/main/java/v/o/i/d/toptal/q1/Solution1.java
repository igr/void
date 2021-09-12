package v.o.i.d.toptal.q1;


/**
 * <div class="brinza-task-description">
 * <p>You would like to find the sentence containing the largest number of words in some given text. The text is specified as a string S consisting of N characters: letters, spaces, dots (<tt style="white-space:pre-wrap">.</tt>), question marks (<tt style="white-space:pre-wrap">?</tt>) and exclamation marks (<tt style="white-space:pre-wrap">!</tt>).</p>
 * <p>The text can be divided into sentences by splitting it at dots, question marks and exclamation marks. A sentence can be divided into words by splitting it at spaces. A sentence without words is valid, but a valid word must contain at least one letter.</p>
 * <p>For example, given S = "<tt style="white-space:pre-wrap">We test coders. Give us a try?</tt>", there are three sentences: "<tt style="white-space:pre-wrap">We test coders</tt>", "<tt style="white-space:pre-wrap"> Give us a try</tt>" and "<tt style="white-space:pre-wrap"></tt>". The first sentence contains three words: "<tt style="white-space:pre-wrap">We</tt>", "<tt style="white-space:pre-wrap">test</tt>" and "<tt style="white-space:pre-wrap">coders</tt>". The second sentence contains four words: "<tt style="white-space:pre-wrap">Give</tt>", "<tt style="white-space:pre-wrap">us</tt>", "<tt style="white-space:pre-wrap">a</tt>" and "<tt style="white-space:pre-wrap">try</tt>". The third sentence is empty.</p>
 * <p>Write a function:</p>
 * <blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int solution(String S); }</tt></p></blockquote>
 * <p>that, given a string S consisting of N characters, returns the maximum number of words in a sentence.</p>
 * <p>For example, given S = "<tt style="white-space:pre-wrap">We test coders. Give us a try?</tt>", the function should return 4, as explained above.</p>
 * <p>Given S = "<tt style="white-space:pre-wrap">Forget  CVs..Save time . x x</tt>", the function should return 2, as there are four sentences: "<tt style="white-space:pre-wrap">Forget  CVs</tt>" (2 words), "<tt style="white-space:pre-wrap"></tt>" (0 words), "<tt style="white-space:pre-wrap">Save time </tt>" (2 words) and "<tt style="white-space:pre-wrap"> x x</tt>" (2 words).</p>
 * <p>Assume that:</p>
 * <blockquote><ul style="margin: 10px;padding: 0px;"><li>the length of S is within the range [<span class="number">1</span>..<span class="number">100</span>];</li>
 * <li>string S consists only of letters (<tt style="white-space:pre-wrap">a</tt>−<tt style="white-space:pre-wrap">z</tt>, <tt style="white-space:pre-wrap">A</tt>−<tt style="white-space:pre-wrap">Z</tt>), spaces, dots (<tt style="white-space:pre-wrap">.</tt>), question marks (<tt style="white-space:pre-wrap">?</tt>) and exclamation marks (<tt style="white-space:pre-wrap">!</tt>).</li>
 * </ul>
 * </blockquote><p>In your solution, focus on <b><b>correctness</b></b>. The performance of your solution will not be the focus of the assessment.</p>
 * </div>
 */
public class Solution1 {

	public static void main(String[] args) {
		new Solution1().solution("We test codes. Give us a try?");
	}

	public int solution(String S) {
		var sentences = splitToSentences(S);
		int wordCounter = 0;
		for (String sentence : sentences) {
			var wordsCount = countWords(sentence);
			if (wordsCount > wordCounter) {
				wordCounter = wordsCount;
			}
		}
		return wordCounter;
	}

	private static String[] splitToSentences(String text) {
		return text.split("[.?!]");
	}

	private static int countWords(String text) {
		int count = 0;
		for (String word : text.split(" ")) {
			if (word.isBlank()) {
				continue;
			}
			count++;
		}
		return count;
	}
}
