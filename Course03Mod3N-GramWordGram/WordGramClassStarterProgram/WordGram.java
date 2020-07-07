public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        //array to hold our shifted words
        String[] shiftedWords = myWords;
    
        //loop thru entire text, if the last word is reached, set last word to new argument word
        for(int i=0; i<myWords.length; i++) {
            if(i + 1 == myWords.length) shiftedWords[i] = word;
    
            //if not simply shift everything 1 index backwards
            else {
                shiftedWords[i] = out.wordAt(i+1);
            }
            out = new WordGram(shiftedWords, 0, shiftedWords.length);
        }
        return out;
    }

    @Override
    public String toString(){
        String ret = "";
        for(int i=0; i<myWords.length; i++) {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    @Override
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;

        //if lengths aren't equal return false
        if(this.length() != other.length()) return false;

        //if any of the words arent equal return false
        for(int i=0; i<myWords.length; i++) {
            if(!myWords[i].equals(other.wordAt(i))) return false; 
        }
        //else return true
        return true;
    }

    @Override
    //enabling the WordGram object be put into a hashmap
    public int hashCode() {
        return toString().hashCode();
    }
}