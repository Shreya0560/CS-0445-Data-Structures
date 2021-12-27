// CS 0445 Fall 2021
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration (i.e. no loops) is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the method in the StringBuilder class from Assignment 2.  
public class MyStringBuilder2
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder2 initialized with the chars in String s.
	// This method is provided to you -- see the Assignment 3 document.
	
	// Constructor to make a new MyStringBuilder2 from a String.itself is NOT recursive – however, it calls a recursive method to do the 
	// actual set up work.  This should be your general approach for all of the 
	// methods, since the recursive methods typically need extra parameters that // are not given in the specification. 
	
	
	public MyStringBuilder2(String s) 
	{  
	if (s != null && s.length() > 0)   
		makeBuilder(s, 0);  
	else  
		// no String so initialize empty MyStringBuilder2  
		{   
			firstC = null;   
			length = 0; 
		}  
	}  
	
	// Recursive method to set up a new MyStringBuilder2 from a String 
	private void makeBuilder(String s, int pos) 
	{  
	// Recursive case – we have not finished going through the String  
		if (pos < s.length()-1)  
		{   
		// Note how this is done – we make the recursive call FIRST, then   
		// add the node before it.  In this way EVERY node we add is  
		// the front node, and it enables us to avoid having to make a   
		// special test for the front node.  However, many of your   
		// methods will proceed in the normal front to back way.  

		
			makeBuilder(s, pos+1);  //(1) -calls stack on one after the other 
			CNode temp = new CNode(s.charAt(pos));  //(3)-rest of code executes,popping off each call as that pos is used, adding each new node to front(not back)  
			CNode back = firstC.prev;   
			temp.prev = back;   
			back.next = temp;   
			temp.next = firstC;   
			firstC.prev = temp;   
			firstC = temp;   
			length++;  
		}  
		else if (pos == s.length()-1) //(2)-last call pops off
			// Special case for last char in String  
			{     
			// This is a base case and initializes       
			// firstC in a circular way   
			firstC = new CNode(s.charAt(pos));   
			firstC.next = firstC;   
			firstC.prev = firstC;   
			length = 1;  
			}  
		else
			// This case should never be reached, due to the way the    
		// constructor is set up.  However, I included it as a  
		{ 
		// safeguard (in case some other method calls this one)   
			length = 0;   
			firstC = null; 
		} 
	}
		

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{ 
		if (s != null && s.length > 0)   
		{ 
			makeBuilder(s, 0);   
		}
		else  
		// empty array so initialize empty MyStringBuilder2  
		{   
			firstC = null;   
			length = 0; 
		}  
	} 
	
	private void makeBuilder(char [] s, int pos) 
	{  
	// Recursive case – we have not finished going through the array  
		if (pos < s.length-1)  
		{      
			makeBuilder(s, pos+1);   
			CNode temp = new CNode(s[pos]);   
			CNode back = firstC.prev;   
			temp.prev = back;   
			back.next = temp;   
			temp.next = firstC;   
			firstC.prev = temp;   
			firstC = temp;   
			length++;  
		}  
		else if (pos == s.length-1) 
			// Special case for last char in String  
			{     
			// This is a base case and initializes       
			// firstC in a circular way   
			firstC = new CNode(s[pos]);   
			firstC.next = firstC;   
			firstC.prev = firstC;   
			length = 1;  
			}  
		else
			// This case should never be reached, due to the way the    
			// constructor is set up.  However, I included it as a  
			{ 
			// safeguard (in case some other method calls this one)   
				length = 0;   
				firstC = null; 
			} 
	}

	// Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder2(MyStringBuilder2 old)
	{ 
		if (old != null && old.length > 0)   
			makeBuilder(old, 0);  
		else  
		// no String so initialize empty MyStringBuilder2  
			{   
				firstC = null;   length = 0; 
			}  
	}
	
	private void makeBuilder(MyStringBuilder2 old, int pos) 
	{  
	// Recursive case – we have not finished going through the array  
		if (pos < old.length-1)  
		{      
			makeBuilder(old, pos+1);   
			CNode temp = new CNode(old.charAt(pos));   
			CNode back = firstC.prev;   
			temp.prev = back;   
			back.next = temp;   
			temp.next = firstC;   
			firstC.prev = temp;   
			firstC = temp;   
			length++;  
		}  
		else if (pos == old.length()-1) 
			// Special case for last char in String  
			{     
			// This is a base case and initializes       
			// firstC in a circular way   
			firstC = new CNode(old.charAt(pos));   
			firstC.next = firstC;   
			firstC.prev = firstC;   
			length = 1;  
			}  
		else
			// This case should never be reached, due to the way the    
			// constructor is set up.  However, I included it as a  
			{ 
			// safeguard (in case some other method calls this one)   
				length = 0;   
				firstC = null; 
			} 
	} 
	public MyStringBuilder2()
	{ 
		firstC = null; 
		length = 0;
	}

	// Return the entire contents of the current MyStringBuilder2 as a String.
	// This method is provided to you -- see Assignment 3 document.
	public String toString() 
	{  
		char [] c = new char[length];  
		getString(c, 0, firstC);  
		return (new String(c)); //all calls will pop off
	}  
	
	private void getString(char [] c, int pos, CNode curr) 
	{  
		if (pos < length) // Not at end of the list  
		{  
			c[pos] = curr.data;  // put next char into array   
			getString(c, pos+1, curr.next);  // recurse to next node and   
		}                                      // next pos in array 
	}  
	
	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{    
		if(index < 0) //had index > length as a condition, but interfered with delete method when end is past length
		{  
			throw new IndexOutOfBoundsException();
		}  
		else 
		{  
			return getcharAt(firstC,0,index); 
		} 
	}  
	private char getcharAt(CNode currNode, int pos, int index) 
	{  
		if(pos < index) 
		{ 
			currNode = currNode.next;  
			return getcharAt(currNode,pos + 1,index); 
			
		}  
		else 
		{ 
			return currNode.data;
		}
	}  
	
	// Return the entire contents of the current MyStringBuilder2 as a String
	// in the reverse of the order that it is stored. 
	public String revString() //passing in a sb, creating a char array with letters in reverse order, returning it as a string
	{ 
		char [] c  = new char[length]; 
		getrevString(c,0,firstC.prev); 
		return (new String(c));
	}  
	
	private void getrevString(char [] c, int pos, CNode curr) 
	{  
		if(pos < length) //Not at end of list
		{ 
			c[pos] = curr.data; 
			getrevString(c, pos + 1, curr.prev);
		}
	} 
	
	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b) 
	{  
		if(b.length == 0) 
		{  
			return this;
		} 
		else 
		{  
			doAppend(firstC.prev,0,b,b.firstC);
		} 
		return this;
	} 
	
	private void doAppend(CNode currNode,int pos,MyStringBuilder2 b,CNode bcurrNode) 
	{ 
		if(pos < b.length) 
		{ 
			CNode newNode = new CNode(bcurrNode.data);
			currNode.next = newNode; 
			newNode.prev = currNode;  
			length ++; 
			doAppend(currNode.next,pos + 1,b,bcurrNode.next);
		} 
		else //base case
		{
			currNode.next = firstC; //making it circular
			firstC.prev = currNode; 
		}
	}  
	
	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{ 
		if( s.length() == 0) 
		{  
			return this;
		} 
		else 
		{  
			if(this.length == 0) 
			{ 
				MyStringBuilder2 A = new MyStringBuilder2(s); 
				return A;
			}
			doAppend(firstC.prev,0,s);
		} 
		return this;
	}  
	
	
	private void doAppend(CNode currNode,int pos,String s) 
	{ 
		if(pos < s.length()) 
		{ 
			CNode newNode = new CNode(s.charAt(pos));
			currNode.next = newNode; 
			newNode.prev = currNode; 
			//currNode = newNode 
			length ++; 
			doAppend(currNode.next,pos + 1,s);
		} 
		else //base case
		{
			currNode.next = firstC;
			firstC.prev = currNode; 
		}
	}  

	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases
	public MyStringBuilder2 append(char [] c)
	{   
		if( c.length == 0) 
		{  
			return this;
		} 
		else 
		{  
			if(this.length == 0) 
			{ 
				MyStringBuilder2 A = new MyStringBuilder2(c); 
				return A;
			}
			doAppend(firstC.prev,0,c);
		} 
		return this;
	}  
	
	private void doAppend(CNode currNode,int pos,char[] c) 
	{  
		if(pos < c.length) 
		{ 
			CNode newNode = new CNode(c[pos]);
			currNode.next = newNode; 
			newNode.prev = currNode; 
			//currNode = newNode 
			length ++; 
			doAppend(currNode.next,pos + 1,c);
		} 
		else //base case
		{
			currNode.next = firstC;
			firstC.prev = currNode; 
		}
	} 
	
	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{ 
		if(this.length == 0) //if StringBuilder is empty
			{ 
				CNode newNode = new CNode(c); 
				firstC = newNode; 
				length ++;
			}
		else
		{  
			CNode currNode = firstC.prev;  
			CNode newNode2 = new CNode(c);  
			currNode.next = newNode2; 
			newNode2.next = firstC; 
			newNode2.prev = currNode;
			firstC.prev = newNode2;
			length ++; 
		} 
		return this;	
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{ 
		char[] c = new char[end - start]; 
		CNode startNode = findStart(firstC,start,0);
		if (end < start)  // special case for empty String
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			getsubstring(c,0,startNode); 
			return new String(c);
		} 
	} 
	private void getsubstring(char [] c,int pos,CNode currNode) //pos represents the index of the substring we are on, curr represents the node of the sb we are taking the next letter from
	{  
		if(pos < c.length) 
		{  
			c[pos] = currNode.data;
			getsubstring(c,pos+1,currNode.next);
	 	}
	} 
	private CNode findStart(CNode startNode,int start,int pos) 
	{  
		if(pos < length) 
		{  
			if(pos == start) 
			{  
				return startNode;
			} 
			startNode = startNode.next;
		} 
		return findStart(startNode,start,pos + 1);
	} 
	
	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	MyStringBuilder2 delete(int start, int end)
	{  
		if(start < 0 || end <= start) 
		{ 
			return this;
		}  
		else if(start == 0) //special case because firstC instance variable would be changing
		{   
			CNode endNode = findNode1(firstC,end,0); 
			firstC.prev = endNode.prev; 
			firstC.next = endNode.next;
			firstC = endNode;   
			length = length - (end - start); 			
		} 
		else if(end > length - 1)  //if end is past the end of the StringBuilder
		{   
			CNode startNode = findNode1(firstC,start - 1,0); 
			firstC.prev = startNode;
			startNode.next= firstC; //deleting the nodes
			length = length - ((length) - start); //updating length
		} 
		
		else 
		{  
			CNode startNode = findNode1(firstC,start - 1,0);  
			CNode endNode = findNode1(firstC,end,0); 
			startNode.next = endNode; 
			endNode.prev = startNode;
			length = length - (end - start);
		} 
				
		return this;
	} 
	
	private CNode findNode1(CNode currNode,int end,int pos) 
	{ 
		if(pos < length) 
		{  
			if(pos == end) 
			{    
				return currNode; 
			} 
			return findNode1(currNode.next,end,pos + 1);
		}  
		else //this case should never be reached, is only reached if start index doesn't is out of bounds
		{  
			return null; 
		}
	}  
	
	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{ 
		CNode currNode = firstC;
		if(index < 0 || index > length - 1) 
		{  
			return this;
		}
			
		else if(length == 1) //special case-only one item in previous string builder, would return empty string builder
		{ 
			firstC = null; 
			length = 0; 
			return this;  
		}
		else if(index == length - 1) //removing last char
		{ 
			CNode lastNode = firstC.prev;    
			CNode beflastNode = lastNode.prev;
			beflastNode.next = firstC; //removes last node
			firstC.prev = beflastNode; 
			length --;
			
		}  
		
		else if(index == 0) //removing first char
		{ 
			CNode secNode = firstC.next; 
			CNode lastNode = firstC.prev;  
			lastNode.next = secNode;  
			secNode.prev = lastNode; 
			firstC = secNode;
			length --;
			
		}
		else //normal case
		{ 
			doDelete(0,index,firstC);
		} 
		return this; 
	} 
	
	private void doDelete(int pos, int index, CNode currNode) 
	{ 
		if(pos < length) 
		{  
			if(pos == index) 
			{  
				currNode.prev.next = currNode.next; 
				currNode.next.prev = currNode.prev;
				length --; 
			}
			doDelete(pos + 1,index,currNode.next);
		} 
	} 
	
	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{ 
		CNode currNode = firstC; 
		return findIndex(str,firstC,0); 
	}  
	
	private int findIndex(String s,CNode currNode,int pos) 
	{ 
		if(pos < length) 
		{  
			if(currNode.data == s.charAt(0)) //initial match found-still need to check rest of string
			{  
				boolean myBoolean = checkRest(s,currNode.next,1,true); //method which checks if subsequent nodes after initial match all match with the string respectively 
				if(myBoolean == true) 
				{ 
					return pos;
				} 
				else 
				{  
					return findIndex(s,currNode.next,pos + 1); //similar to backtracking
				}
			} 
			else 
			{  
				return findIndex(s,currNode.next,pos + 1);
			}
		} 
		else //(pos == length)-index hasn't been found
		{  
			return -1;
		} 
	}
	private boolean checkRest(String s,CNode tempNode,int pos,boolean myBoolean) 
	{  
		if(pos < s.length()) 
		{ 
			if(tempNode.data != s.charAt(pos)) 
			{  
				myBoolean = false; 
				return myBoolean;
			} 
			return checkRest(s,tempNode.next,pos + 1,myBoolean);
		} 
		else  //each value in string has been checked with tempNode.data and false hasn't been returned, so the match must be true
		{
			return true;
		}
	}  
	
	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{ 
		if(offset == length) 
		{  
			this.append(str);   
		}  
		else if(offset < 0 || offset > length) 
		{  
			return null;
		}  
		
		else if(offset == 0) 
		{  
			CNode currstringNode = new CNode(str.charAt(0));  
			CNode nextNode = firstC; 
			//currstringNode.prev = firstC.prev;
			firstC.prev.next = currstringNode; 
			currstringNode.prev = firstC.prev;
			firstC = currstringNode;   
			CNode newNode = currstringNode; 
			doInsert(str,1,currstringNode,nextNode); //inserting rest of string characters(after first character)
		} 
		else 
		{  
			CNode tempNode = findOffset(str,1,firstC,offset);//can start at pos = 1 because we know offset isn't at pos = 0, saves extra iteration
			doInsert(str,0,tempNode,tempNode.next); 
		}
		return this;
	} 
	private CNode findOffset(String s,int pos,CNode currNode,int offset) 
	{ 
		if(pos < length) 
		{  
			if(pos == offset) 
			{    
				return currNode; 
			} 
			return findOffset(s,pos + 1, currNode.next,offset);
		}  
		else //this case should never be reached, is only reached if offset index doesn't is out of bounds
		{  
			return null; 
		}
	}  
		
	private void doInsert(String s,int pos,CNode currNode,CNode nextNode) //nextNode represents the node to connect the last node from the string to
	{ 
		if(pos < s.length()) 
		{  
			CNode newNode = new CNode(s.charAt(pos)); 
			currNode.next = newNode; 
			newNode.prev = currNode;
			doInsert(s,pos + 1,currNode.next,nextNode);
		} 
		else //base case
		{  
			currNode.next = nextNode; //connecting it to the next node from the original stringbuilder
			nextNode.prev = currNode; 
			length = length + s.length();	 
		}
	} 

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{ 
		if(offset == length) 
		{  
			this.append(c);   
		}  
		else if(offset < 0 || offset > length) 
		{  
			return null;
		}  
		
		else if(offset == 0) 
		{  
			CNode currcharNode = new CNode(c);  
			CNode nextNode = firstC; 
			firstC.prev.next = currcharNode; 
			currcharNode.prev = firstC.prev;
			firstC = currcharNode;    
			currcharNode.next = nextNode; //connecting it to the beginning of the string builder 
			nextNode.prev = currcharNode; 
			length ++;	 
		} 
		else 
		{  
			CNode tempNode = findOffset(c,1,firstC,offset);//can start at pos = 1 because we know offset isn't at pos = 0, saves extra iteration
			CNode newNode = new CNode(c);  
			tempNode.prev.next = newNode; 
			newNode.prev = tempNode; 
			newNode.next = tempNode; 
			tempNode.prev = newNode;
		} 
		return this;
	} 
	private CNode findOffset(char c,int pos,CNode currNode,int offset) 
	{ 
		if(pos < length) 
		{  
			if(pos == offset) 
			{  
				//CNode tempNode = currNode;  
				return currNode; 
			} 
			return findOffset(c,pos + 1, currNode.next,offset);
		}  
		else //this case should never be reached, is only reached if offset index doesn't is out of bounds
		{  
			return null; 
		}
	}  
		
	
 
	// Return the length of the current MyStringBuilder2
	public int length()
	{ 
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	
	public MyStringBuilder2 replace(int start, int end, String str) 
	{ 
		CNode startNode; 
		boolean findFirstC = false;
		if(start < 0 || end <= start) 
		{  
			return null;
		} 
		else if(start == 0) //special case 
		{
			startNode = firstC.prev;  
			findFirstC = true; //we need to change firstC only in this case
		} 
		else 
		{  
			startNode = findNode2(str,0,firstC,start-1); 
		} 
		
		
		if(end > length - 1) 
		{ 
			doReplace(str,0,startNode,firstC,findFirstC); 
			//length = length + (end -(length -1)); //need to update the length since inserting past end of the string builder  
			length += str.length() - (length - start);
		}  
		else if((end-start) > str.length())
		{  
			//end = end - start
			CNode endNode = findNode2(str,start - 1,startNode,end);
			doReplace(str,0,startNode,endNode,findFirstC); 
			length = length - ((end-start) - str.length()); 
		}
		else if ((end-start) < str.length())  
		{  
			CNode endNode = findNode2(str,start - 1,startNode,end); 
			doReplace(str,0,startNode,endNode,findFirstC); 
			length = length + (str.length() - (end - start)); 
		} 
		else 
		{  
			//end = end - start
			CNode endNode = findNode2(str,0,firstC,end); 
			doReplace(str,0,startNode,endNode,findFirstC); 
			length = length; //str.length is equal to (end-start)
		}
		  
		return this;		
	}		
	
	private CNode findNode2(String s,int pos,CNode currNode,int end) 
	{ 
		if(pos < length) 
		{  
			if(pos == end) 
			{    
				return currNode; 
			} 
			return findNode2(s,pos + 1, currNode.next,end);
		}  
		else //this case should never be reached, is only reached if start index is out of bounds
		{  
			return null; 
		}
	}  
		
	 private void doReplace(String s,int pos,CNode startNode,CNode endNode,boolean findFirstC) 
	{ 
		if(pos < s.length()) 
		{  
			CNode newNode = new CNode(s.charAt(pos));  
			if(findFirstC) 
			{ 
				firstC = newNode; 
			}
			startNode.next = newNode; 
			newNode.prev = startNode;
			doReplace(s,pos + 1,startNode.next,endNode,false);
		} 
		else //base case
		{  
			startNode.next = endNode; //removes the nodes
			endNode.prev = startNode; 
		}
	}   
	
	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	
	public int lastIndexOf(String str)
	{ 
		//CNode currNode = firstC.prev; 
		return findIndex2(str,firstC.prev,length - 1); 
	}  
	
	private int findIndex2(String s,CNode currNode,int pos) 
	{ 
		if(pos > -1) //this pos represents the index of the string builder 
		{  
			if(currNode.data == s.charAt(s.length() -1)) //Need to check from last character of string because moving from back to front(initial match found)
			{  
				boolean myBoolean = checkRest2(s,currNode.prev,s.length() - 2,true); //method which checks if subsequent nodes after initial match all match with the string respectively 
				if(myBoolean == true) 
				{ 
					return pos-(s.length() - 1); //need to do -(s.length() -1) because pos holds the index of the end of the string in the stringbuilder. But we want the index of the first character of string in the string builder
				} 
				else 
				{  
					return findIndex2(s,currNode.prev,pos - 1);
				}
			} 
			else 
			{  
				return findIndex2(s,currNode.prev,pos - 1);
			}
		} 
		else //(pos == length), so index hasn't been found
		{  
			return -1;
		} 
	}
	private boolean checkRest2(String s,CNode tempNode,int pos,boolean myBoolean) 
	{  
		if(pos > -1) //this pos represents the index of the string as we are checking if all the characters in the string match 
		{ 
			if(tempNode.data != s.charAt(pos)) 
			{  
				myBoolean = false; 
				return myBoolean;
			} 
			return checkRest2(s,tempNode.prev,pos - 1,myBoolean);
		} 
		else  //each value in string has been checked with tempNode.data and false hasn't been returned, so the match must be true
		{
			return true;
		}
	}  
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
 	/* public MyStringBuilder2 [] regMatch(String [] pats)
	{ 
		MyStringBuilder2[] Match  = new MyStringBuilder[pats.length]; 
		return doregMatch(pats,this,0,firstC);  
		
	} 
	
	private MyStringBuilder2 doregMatch(String[] pats,MyStringBuilder2 B,int pos,CNode BcurrNode) 
	{ 
		MyStringBuilder2 A = new MyStringBuilder2(); 
		CNode AcurrNode;
		if(pos < length) //this pos is keeping track of the starting indexes we check. If we continue after 1 initial match and it doesn't work, we need to backtrack back to the next pos-save this pos
		{  
			boolean first = checkMatch1(pats,B,pos,0,0,BcurrNode); 
			if(first == true) //initial match found-still need to check rest of stringBuilder 
			{  
				CNode firstNode = BcurrNode.data; 
				A.firstC = firstNode; 
				AcurrNode = firstC; 
				return checkMatch2(pats,B,pos + 1,0,BcurrNode.next,A);
				
			else 
			{  
				return doregMatch(s,currNode.next,pos + 1); //initial match not find, try with next pos of B
			}
		} 
		else //(pos == length)-index hasn't been found
		{  
			return -1;
		} 
	} 
	
	private boolean checkMatch1(String[] pats,MyStringBuilder2 Match,int pos,int patspos,int pat,CNode BcurrNode) //pos is index of B, patspos is index of charAt within a stirng in the pats array, pat is the index of the string in pats
	{  
		if(patspos < pats[pat].length) 
		{ 
			if(Match.charAt(pos) == pats[pat].charAt(patspos)) 
			{   
				return true;
			} 
			else 
			{  
				return checkMatch(pats,Match,pos,patspos + 1,pat,BcurrNode.next); //looping through the different chars in first pat  to check if one of them match with the char at pos of B
			} 
		} 
		else 
		{  
			return false;
		}
	}  
	
	private String checkMatch2(String[] pats,MyStringBuilder2 Match,int pos,int patspos,int pat,CNode BcurrNode,MyStringBuilder2 A) //pos is index of B, patspos is index of charAt within a stirng in the pats array, pat is the index of the string in pats
	{  
		if(patspos < pats[pat].length) 
		{ 
			if(Match1(pats,Match,pos,patspos,pat,BcurrNode))
			{   
				A.next = BcurrNode;
				checkMatch2(pats,Match,pos + 1,patspos,pat,BcurrNode.next,A);
			} 
			else 
			{  
				return A; //base case reached
			} 
		} 
		else 
		{  
			return A; //rare case-all of the chars in pa
		}
	}  */
	
	
	
	
	 
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next, prev;

		public CNode(char c)
		{
			data = c;
			next = null;
			prev = null;
		}

		public CNode(char c, CNode n, CNode p)
		{
			data = c;
			next = n;
			prev = p;
		}
	} 
	
}
