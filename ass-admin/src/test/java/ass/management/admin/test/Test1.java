package ass.management.admin.test;

public class Test1 {

    protected String name;

    private String age;

    private TestIn testIn;

    public Test1() {
        this.testIn = new TestIn();
    }

    public class TestIn{
        private String idea;

        public String getIdea() {
            return idea;
        }
        public void setIdea(String idea) {
            this.idea = idea;
        }

        @Override
        public String toString() {
            return "testIn{" +
                    "idea='" + idea + '\'' +
                    '}';
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public TestIn getTestIn() {
        return testIn;
    }
    public void setTestIn(TestIn testIn) {
        this.testIn = testIn;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", testIn=" + testIn +
                '}';
    }

    public static void main(String[] args){
        Test1 t1 = new Test1();
        t1.setAge("123");
        t1.setName("aaa");
        System.out.println(t1);

        t1.getTestIn().setIdea("11111111");

        System.out.println(t1);
    }


}
