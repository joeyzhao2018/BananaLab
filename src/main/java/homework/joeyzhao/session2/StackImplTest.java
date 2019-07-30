package homework.joeyzhao.session2;

public class StackImplTest {

    public static void main(String[] args){

        StackImpl s=new StackImpl();
        testStack(s);

    }

    static void testStack(Stack s) {
        s.push(1);
        s.push(2);
        int pop1=s.pop();
        System.out.println("Popped : "+pop1);
        System.out.println("Now the peak is: "+s.peak());
        s.pop();
        try{
            System.out.println(s.peak());
        }catch (Exception e){
            System.out.println("Peak() got Exception: "+e.getMessage());
        }
        try{
            System.out.println(s.pop());
        }catch (Exception e){
            System.out.println("Pop() got Exception: "+e.getMessage());
        }
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.print("Now the size is "+s.size());
    }
}
