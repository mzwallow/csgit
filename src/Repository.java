import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.DuplicateFormatFlagsException;
import javax.naming.NameNotFoundException;

public class Repository {
    private static String defaultBranchName = "master";

    private List<Branch> branches;
    private Branch mainBranch;

    private static Repository instance;

    private Repository() {
        this.branches = new ArrayList<>();

        if (this.mainBranch == null) {
            this.mainBranch = new Branch(Repository.defaultBranchName);
            this.branches.add(this.mainBranch);
        }
    }

    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();

        return instance;
    }

    public void commit(String branchName, String[] files, String message) throws Exception {
        Branch branch = findBranch(branchName);
        if (branch == null)
            throw new NameNotFoundException("Branch '" + branchName + "' not found");

        branch.commit(files, message);
    }

    public void createBranch(String newBranchName, String oldBranchName) throws Exception {
        Branch branch = findBranch(newBranchName);
        if (branch != null)
            throw new DuplicateFormatFlagsException("Branch '" + newBranchName + "' already exists");

        Branch oldBranch = findBranch(oldBranchName);
        if (oldBranch == null)
            throw new NameNotFoundException("Branch '" + oldBranchName + "' not found");

        Branch newBranch = new Branch(newBranchName, oldBranch);
        this.branches.add(newBranch);
    }

    public void printBranchInfo(String branchName) throws Exception {
        Branch branch = findBranch(branchName);
        if (branch == null)
            throw new NameNotFoundException("Branch '" + branchName + "' not found");

        branch.printBranchInfo();
    }

    private Branch findBranch(String name) {
        Iterator<Branch> iterator = branches.iterator();
        while (iterator.hasNext()) {
            Branch branch = iterator.next();
            if (branch.getName().equals(name))
                return branch;
        }

        return null;
    }
}
