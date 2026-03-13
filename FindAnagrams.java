private void findAnagrams() {

    ArrayList<String> keysToGet = new ArrayList<>();

    StringBuilder sb_words = new StringBuilder();

    for (String key : mm_words.keySet()) {

        if (!isCancelledFromDialog || isCancelled()) {
            //Finish loop, user max results have been reached
            if (matchedWords.size() == this.wordLimit) {
                break;
            }

            //If word is larger than given letters then it can't be made
            if (key.length() > originalLetters.length()) {
                continue;
            }

            //Deleting what was left of previous letters
            sb_words.delete(0, sb_words.length());
            //Reload original letters
            sb_words.append(originalLetters);

            for (int i = 0; i < key.length(); i++) {

                int indexOfChar = sb_words.indexOf(String.valueOf(key.charAt(i)));

                //If char isn't found in string break as word can't be made | -1 = false for indexOf method
                if (indexOfChar == -1) {
                    break;
                } else {

                    //Letter is contained, get the index of this letter so it can be removed from sb_words
                    sb_words.delete(indexOfChar, indexOfChar + 1);

                }

                if (i == key.length() - 1) { //All letters were remove meaning word was found

                    keysToGet.add(key);
                }
            }

        } else {
            isCancelledFromDialog = true;
            cancel(true);
            break;
        }
    }

    matchedWords.clear();

    for (String retrieveKey : keysToGet) {

        matchedWords.addAll(mm_words.get(retrieveKey));
    }

    sortWordsFound();

    setAmountFound(matchedWords);
}
