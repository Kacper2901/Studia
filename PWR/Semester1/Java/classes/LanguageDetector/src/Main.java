void main(String[] args) throws IOException{
    LanguageDetector languageDetector1 = new LanguageDetector();
    Languages guess = languageDetector1.getLanguageOfInputText(2);
    System.out.println("\n\nI think it's... " + guess.toString() + "!");
}