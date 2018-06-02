import myapp.NotesContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotesContainerTests {

    NotesContainer _testInstance = NotesContainer.getInstance();

    @Before
    public void setUp() throws Exception {
        _testInstance.clear();
    }

    @Test
    public void ForEach_LastElement_MustBeFirstInsertedNote() {

        var firstNote = "First";
        _testInstance.addNote(firstNote);
        _testInstance.addNote("Second");
        _testInstance.addNote("Third");


        String lastIterNote = null;
        for (String note : _testInstance) {
            lastIterNote = note;
        }

        Assert.assertEquals(lastIterNote, firstNote);
    }

    @Test
    public void ForEach_FirstElement_MustBeLastInsertedNote() {
        var lastNote = "Last";
        _testInstance.addNote("First");
        _testInstance.addNote("Second");
        _testInstance.addNote("Third");
        _testInstance.addNote(lastNote);

        boolean firstRead = false;
        String firstIterNote = null;
        for (String note : _testInstance) {

            if(! firstRead) {
                firstIterNote = note;
                firstRead = true;
            }
        }

        Assert.assertEquals(firstIterNote, lastNote);
    }
}
