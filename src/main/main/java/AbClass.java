abstract class AbClass {
    public AbClass() {
    }

    abstract void onClick();

    public void isOnClick(){
        System.out.println("시작");
        onClick();
        System.out.println("끝");
    }
}
