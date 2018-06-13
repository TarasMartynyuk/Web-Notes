import myapp.notes.Note;
import myapp.notes.NotesContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotesContainerTests {

    static final Note FIRST_NOTE = new Note("First");
    static final Note SECOND_NOTE = new Note("Second");
    static final Note THIRD_NOTE = new Note("Third");

    NotesContainer _testInstance = NotesContainer.getInstance();

    @Before
    public void setUp() {
        _testInstance.clear();

        _testInstance.addNote(FIRST_NOTE);
        _testInstance.addNote(SECOND_NOTE);
        _testInstance.addNote(THIRD_NOTE);
    }

    @Test
    public void ForEach_LastElement_MustBeFirstInsertedNote() {

        Note lastIterNote = null;
        for (var note : _testInstance.listNotes()) {
            lastIterNote = note;
        }

        Assert.assertEquals(lastIterNote, FIRST_NOTE);
    }

    @Test
    public void ForEach_FirstElement_MustBeLastInsertedNote() {

        boolean firstRead = false;
        Note firstIterNote = null;
        for (var note : _testInstance.listNotes()) {

            if(! firstRead) {
                firstIterNote = note;
                firstRead = true;
            }
        }

        Assert.assertEquals(firstIterNote, THIRD_NOTE);
    }

//    @Test
//    public void AddNote_MustBeLocked_IfIterationInProgress() throws InterruptedException, ExecutionException {
//
//        long iterDuration = 1000;
//        long totalIterDuration = _testInstance.size() * iterDuration;
//        long tolerance = 50;
//
//        Runnable iteration = () -> {
//
//            for(String note : _testInstance.listNotes()) {
//                System.out.println("iter started");
//                try {
//                    Thread.sleep(iterDuration);
//                    System.out.println("iter done for : " + note);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Callable<Long> addingNote = () -> {
//            long startTime = System.currentTimeMillis();
//
//            // make sure that iteration has started
//            Thread.sleep(tolerance / 4);
//            System.out.println("add note started");
//            _testInstance.addNote("New Note");
//            long endTime = System.currentTimeMillis();
//
//            return endTime - startTime;
//        };
//
//        var iterThread = new Thread(iteration);
//        iterThread.start();
//        var addNoteTask =  Executors.newSingleThreadExecutor().submit(addingNote);
//
//        long additionDuration = addNoteTask.get();
//        iterThread.join();
//
//        Assert.assertTrue(additionDuration + tolerance >= totalIterDuration);
//    }
//
////    @Test
//    public void ForEach_IfAddingNotesInParallelWithIterating_IteratedValuesRemainUnchanged() {
//    }
}
