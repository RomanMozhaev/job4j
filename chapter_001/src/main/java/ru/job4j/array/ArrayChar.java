package ru.job4j.array;
/**
* ArrayChar difinds if the prefix is the first part of word.
* @author RomanM
* @version 1.0 April 23, 2019
*/
public class ArrayChar {
    private char[] data;
    /**
	* Constructor take a line/ a word and returns the array of chars.
	* @param line - the word which we are going to check.
	*/
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }
    /**
     * startWith checks that the word starts a prefix.
     * @param prefix - the prefix.
     * @return true if the word starts a prefix.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
		// Если префикс длинее чем слово, то возвращает false
        char[] value = prefix.toCharArray();
		if (value.length > data.length) {
			return false;
		}
		// проверить, что массив data имеет первые элементы, одинаковые с value
		for (int i = 0; i < value.length; i++) {
			if (data[i] != value[i]) {
				return false;
			}
		}
			
        return result;
    }
}