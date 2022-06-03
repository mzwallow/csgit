public class CSGit {
    public static void main(String[] args) throws Exception {
        Repository repo = Repository.getInstance();

        System.out.println("---------------");

        // Commit to 'master'
        String[] changes1 = { "main.go", "go.mod", "go.sum" };
        repo.commit("master", changes1, "🎉 Initial commit");

        // Commit to 'master'
        String[] changes2 = { "utils/calculator.go", "main.go" };
        repo.commit("master", changes2, "✨ Add calculator");

        // Create new brach 'convertor'
        repo.createBranch("convertor", "master");

        // Commit to 'convertor'
        String[] changes3 = { "utils/convertor.go", "main.go" };
        repo.commit("convertor", changes3, "✨ Add convertor");

        // Commit to 'master'
        String[] changes4 = { "main.go", "main_test.go" };
        repo.commit("master", changes4, "✅ Add calculator tests");

        // Commit to 'convertor'
        String[] changes5 = { "main.go", "main_test.go" };
        repo.commit("convertor", changes5, "✅ Add convertor tests");

        repo.printBranchInfo("master");
        System.out.println("---------------");
        repo.printBranchInfo("convertor");

        System.out.println("---------------");
    }
}
