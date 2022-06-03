import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.UUID;

public class Commit {
    private String id;
    private LocalDateTime dateTime;
    private String message;
    private String[] changes;

    public Commit parentCommit;
    public Commit nextCommit;

    public Commit(String[] files, String message) throws Exception {
        this.id = generateSHA1FromUUID();
        this.dateTime = LocalDateTime.now();
        this.changes = files;
        this.message = message;
    }

    public String getID() {
        return this.id;
    }

    public void printCommitInfo() {
        System.out.println("===Commit===");
        System.out.println("ID: " + this.id);
        System.out.printf("Parent commit: %s\n",
                this.parentCommit == null ? "null" : this.parentCommit.id);
        System.out.printf("Next commit: %s\n",
                this.nextCommit == null ? "null" : this.nextCommit.id);
        System.out.println("Date: " + this.dateTime);
        System.out.println("Message: " + this.message);
        System.out.println("List of changed files: ");
        for (String change : this.changes) {
            System.out.printf("\t%s\n", change);
        }
    }

    private String generateSHA1FromUUID() throws Exception {
        String sha1 = "";

        UUID uuid = UUID.randomUUID();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.reset();
        md.update(uuid.toString().getBytes("utf8"));
        sha1 = String.format("%040x", new BigInteger(1, md.digest()));

        return sha1;
    }
}
