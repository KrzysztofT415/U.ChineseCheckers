import rules.GameRule;

public class GameRulesIterator implements Iterator{

    GameRule[] rules;
    int j;

    public  GameRulesIterator (GameRule[] rules)
    {
        this.rules = rules;
    }

    public GameRule next()
    {
        GameRule rule =  rules[j];
        j++;
        return rule;
    }

    public boolean hasNext()
    {
        if (j >= rules.length || rules[j] == null)
            return false;
        else
            return true;
    }

}