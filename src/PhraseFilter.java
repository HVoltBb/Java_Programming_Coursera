public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;

    public PhraseFilter(String wh, String term){
        if(wh.equals("start") || wh.equals("end") || wh.equals("any")) {
            where = wh;
        } else {
            where = "any";
            System.out.println("Not a valid location, searching the phrase anywhere in the title.");
        }
        phrase = term;
        name = "Phrase filter";
    }

    public boolean satisfies(QuakeEntry qe){
        switch (where){
            case "start":
                if (qe.getInfo().indexOf(phrase) == 0) return true;
                break;
            case "end":
                if (qe.getInfo().indexOf(phrase) == (qe.getInfo().length() - phrase.length())) return true;
                break;
            case "any":
                if (qe.getInfo().contains(phrase)) return true;
                break;
        }

        // never reached
        return false;
    }

    public String getName(){
        return name;
    }



}
