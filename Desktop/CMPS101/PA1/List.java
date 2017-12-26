/*
 CMPS 101 FALL 2017
 Student: Leon Pham
 ID: 1595392
 Assignment: pa1
 */


public class List {

    private class Node{
        int data;
        Node prev;
        Node next;

        //constructor with bi-directional "pointers"
        public Node(int data) {this.data = data; prev = null; next = null;}

        public String toString(){
            return String.valueOf(data);
            
        }
        
        public boolean equals(Object x){
            boolean eq = false;
            Node that;
            if(x instanceof Node){
                that = (Node) x;
                eq = (this.data==that.data);
            }
            return eq;
        }
    }

    //List class attributes
    private Node front, back, cursor;
    private int length, index;

    public List(){
        clear();
    }

    boolean isEmpty() {
        return length==0;
    }

    public int length(){
        return length;
    }

    public int index(){
        // Returns the index of the cursor element in this which which is set to -1 if undefined;
        if (cursor == null)
            index = -1;
        return index;
    }

    public int front() {
        if (this.isEmpty()) {
            throw new RuntimeException("List Error: front() called on empty List");
        }
        return front.data;
    }

    public int back(){
        if( this.isEmpty() ) throw new RuntimeException ("List Error: back() called on empty List");
        return back.data;
    }

    public int get() throws RuntimeException{
        if(this.isEmpty() && index() >= 0 )
            return cursor.data;
        else
            return cursor.data = -1;
    }

    public boolean equals(List L){
        boolean equiv = false;
        Node N = this.front,M = L.front;
        List A = new List();
        
        if (L instanceof List){
            A = L;
            equiv = (this.length == A.length);
            while( equiv && N != null ){
                equiv = N.equals(M);
                N = N.next;
                M = M.next;
            }
        }
        return equiv;
    }


    public void clear(){
        front = back = cursor = null;
        length = 0;
        index = 0;
    }

    public void moveFront(){
        if (length() > 0)
            cursor = front;
        index = 0;
    }

    public void moveBack(){
        if (length() > 0)
            cursor = back;
        index = length - 1;
    }

    public void movePrev(){
        if (cursor != null){
            if ( cursor != this.front ){
                cursor = cursor.prev;
                index--;
            }
            else{
                cursor = null;
            }
        }
    }

    public void moveNext(){
        if (cursor != null){
            if( cursor != this.back ){
                cursor = cursor.next;
                index++;
            }
            else{
                cursor = null;
            }
        }
    }

    public void prepend(int data){

        Node newData = new Node(data);

        if (length() <= 0){
            front = newData;
            back = newData;
        }
        else{
            front.prev = newData;
            newData.next = front;
            front = newData;
            index++;
        }
        length++; //regardless, we update length
    }

    public void append(int data){
        // Insert new element into this List. If List is non-empty,
        // insertion takes place after back element.
        Node newData = new Node(data);

        if ( length() <= 0 ){
            front = newData;
            back = newData;
        }
        else{
            back.next = newData;
            newData.prev = back;
            back = newData;
        }
        length++;

    }

    public void insertBefore(int data){
        if (length() == 0 || index < 0 ) throw new RuntimeException("insertBefore() called on null cursor");
        if( index >= length())
            throw new RuntimeException("Error");
        else {
            if(length() == 1) //single node
                prepend(data);
            else{
                Node beforeCursor = new Node(data); //create new node
                beforeCursor.next = cursor;
                beforeCursor.prev = cursor.prev;
                if (index != 0){
                    cursor.prev.next = beforeCursor;
                    index++;
                }
                cursor.prev = beforeCursor;
                length++;
            }
        }

    }

    public void insertAfter(int data){
        if (length() == 0 || index < 0 )
            throw new RuntimeException("insertAfter() called illegally");
        if ( index >= length())
            throw new RuntimeException("Error");
        else{
            if(index == length - 1)
                append(data);
            else{
                Node afterCursor = new Node(data);
                Node prevN = cursor.next;
                afterCursor.next = cursor.next;
                afterCursor.prev = prevN;
                if (index != length -1 )
                    prevN.prev = afterCursor;
                cursor.next = afterCursor;
        
                length++;
            }
        }
    }

    public void deleteFront(){
        // Insert new element before cursor.
        // Pre: length()>0, index()>=0
        if (this.isEmpty()) throw new RuntimeException("the list is empty");
        else{
            length--;
            front = front.next;
            if (index == 1)
                index--;
        }
    }

    public void deleteBack(){
        if (this.isEmpty()) throw new RuntimeException("the list is empty");
        else{
            length--;
            back = back.prev;
            if (index == 1)
                index--;
        }

    }

    public void delete(){
        if (length() == 0 || index() < 0) throw new RuntimeException("error");
        else{
            cursor.next = cursor.prev;
            if( cursor == front ){
                front = front.next;
            }
            else if( cursor == back){
                back = back.prev;
            }
            else{
                cursor.prev.next = cursor.next;
            }
            cursor = null;
            index = -1; //undefined means that its -1
            length--;
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N = front;
        while(N!=null){
            sb.append(" ");
            sb.append(N.toString());
            N = N.next;
        }
        return new String(sb);
    }
    public List copy(){

        List newList = new List();
        Node copyNode = front; //insert a node at the front and chain more nodes
        while( copyNode != null ){
            newList.append(copyNode.data);
            copyNode = copyNode.next;
        }
        return newList;
    }

}
