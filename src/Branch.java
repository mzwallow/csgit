public class Branch {
    private String name;

    private Commit tail;
    private Commit head;

    private Branch parentBranch;

    public Branch(String newBranchName) {
        this.name = newBranchName;
    }

    public Branch(String newBranchName, Branch oldBranch) {
        this.name = newBranchName;
        this.parentBranch = oldBranch;
    }

    public String getName() {
        return this.name;
    }

    public void commit(String[] files, String message) throws Exception {
        Commit newCommit = new Commit(files, message);

        // Check if it's a first commit 🎉
        if (this.tail == null) {
            if (this.parentBranch != null)
                newCommit.parentCommit = this.parentBranch.head;

            this.tail = newCommit;
            this.head = this.tail;
        } else {
            this.head.nextCommit = newCommit;
            newCommit.parentCommit = this.head;
            this.head = newCommit;
        }
    }

    public void printBranchInfo() {
        System.out.println("===Branch===");
        System.out.printf("Name: %s\nTAIL: %s\nHEAD: %s\n",
                this.name,
                this.tail == null ? "null" : this.tail.getID(),
                this.head == null ? "null" : this.head.getID());

        if (this.head != null)
            this.head.printCommitInfo();
    }
}
