public class Main {

    public static void main(String args[]){
        AInterface aInterface = new AInterface() {
            @Override
            public int main2() {
                return 0;
            }
        };
        AbClass abClass = new AbClass(){
            @Override
            void onClick() {

            }

        };
        abClass.isOnClick();
    }
}
