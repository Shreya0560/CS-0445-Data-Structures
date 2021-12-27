//fix empty constructor, fix reverseString, fix append (incompatible types), ask about charAt-condition, ask about deleting method(calling mult inst var)
// CS 0445 Fall 2021
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a circular, doubly linked list of nodes.  See more comments below on the
// specific requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
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

	// Create a new MyStringBuilder initialized with the chars in String s
	// Note: This method is implemented for you.  See A2Help.java
	
	public MyStringBuilder() 
	{  
		firstC = null; 
		length = 0;
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{ 
		if (s.length == 0)  // special case for empty String
		{
		firstC = null;
		length = 0;
		}
		else
		{
		firstC = new CNode(s[0]);  // create first node
		length = 1;
		CNode currNode = firstC;
		// Iterate through remainder of the String, creating a new
		// node at the end of the list for each character.  Note
		// how the nodes are being linked and the current reference
		// being moved down the list. 
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				newNode.prev = currNode;
				currNode = newNode;
				length++;
			}
			// Connect end back to front to make list circular
			currNode.next = firstC;
			firstC.prev = currNode; 
		} 
		s.toString();
	
	}
	
	// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder(MyStringBuilder old)
	{ 
		if (old.length == 0)  // special case for empty String
		{
		firstC = null;
		length = 0;
		}
		else
		{
		firstC = new CNode(old.charAt(0));  // create first node
		length = 1;
		CNode currNode = firstC;
		// Iterate through remainder of the String, creating a new
		// node at the end of the list for each character.  Note
		// how the nodes are being linked and the current reference
		// being moved down the list.  
			for (int i = 1; i < old.length; i++)
			{
				CNode newNode = new CNode(old.charAt(i));
				currNode.next = newNode;
				newNode.prev = currNode;
				currNode = newNode;
				length++;
			}
			// Connect end back to front to make list circular
			currNode.next = firstC;
			firstC.prev = currNode; 
		} 
		old.toString();
		
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s.charAt(0));  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  Note
			// how the nodes are being linked and the current reference
			// being moved down the list.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				newNode.prev = currNode;
				currNode = newNode;
				length++;
			}
			// Connect end back to front to make list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}  
		s.toString();
	}
	
// Create and return a String from the characters in the
// MyStringBuilder.  To do this efficiently, we first make
// an array of the appropriate size, fill it with the
// characters, and then construct and return a String from
// that array.  Note that generally you CANNOT use arrays to
// implement your methods but for this method (and substring()
// also) we are using an array just for the purposes of
// storing the characters in order to return a new String.
public String toString()
{
	char [] c = new char[length];
	int i = 0;
	CNode currNode = firstC;
	while (i < length)
	{
		c[i] = currNode.data;
		i++;
		currNode = currNode.next;
	}
	return new String(c);
}
	public MyStringBuilder append(MyStringBuilder b)
	{ 
		CNode currNode = firstC.prev;
		if (b.length == 0)  // special case for empty String
		{
		return this;
		}
		else
		{
			CNode addNode = b.firstC;  
			
			for(int i = 0; i < b.length; i++) 
			{   
				CNode newNode = new CNode(addNode.data);
				currNode.next = newNode; 
				newNode.prev = currNode; 
				currNode = newNode; 
				addNode = addNode.next;				
				length ++;
			} 
			currNode.next = firstC;
			firstC.prev = currNode; 
		} 
		return this;
	}

	 // Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	 public MyStringBuilder append(String s)
	{   
		if (s.length() == 0)  // special case for empty String
		{
		return this;
		}
		else
		{  
			if(this.length == 0) 
			{ 
				MyStringBuilder A = new MyStringBuilder(s); 
				return A;
			}
			
			CNode currNode = firstC.prev; 
			for(int i = 0; i < s.length(); i++) 
			{   
				CNode newNode = new CNode(s.charAt(i));  
				currNode.next = newNode; 
				newNode.prev = currNode; 
				currNode = newNode; 
				length ++;
			} 
			currNode.next = firstC;
			firstC.prev = currNode; 
		} 
		return this; 
	}
	
	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{  
		if (c.length == 0)  // special case for empty String
		{
		return this;
		}
		else
		{ 
			if(this.length == 0) //if StringBuilder is empty
			{ 
				MyStringBuilder A = new MyStringBuilder(c);
			}
			
			CNode currNode = firstC.prev;
			for(int i = 0; i < c.length; i++) 
			{   
				CNode newNode = new CNode(c[i]);  
				currNode.next = newNode; 
				newNode.prev = currNode; 
				currNode = newNode; 
				length ++;
			} 
			currNode.next = firstC;
			firstC.prev = currNode; 
		} 
		return this;
		
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
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
 
	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException. 
	 public char charAt(int index)
	{ 
		CNode currNode = this.firstC; 
		if(index < 0) //had index > length as a condition, but interfered with delete method when end is past length
		{  
			throw new IndexOutOfBoundsException();
		} 
		else 
		{  
			for(int i = 0; i < index; i ++) 
			{  
				currNode = currNode.next;  
			} 
		} 
		return currNode.data;
	} 

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	MyStringBuilder delete(int start, int end)
	{ 
		CNode currNode = firstC; 
		CNode startNode = new CNode(charAt(start)); 
		CNode endNode = new CNode(charAt(end));
		if(start < 0 || end <= start) 
		{ 
			return this;
		} 
		else if(end > length - 1)  //if end is past the end of the StringBuilder
		{ 
			for(int i = 0; i < length; i++) 
			{   
				if(i == start) 
				{    
					currNode.next= firstC; //deleting the nodes
					break;
				}  
				currNode = currNode.next; 
			}  
			firstC.prev = currNode;
			length = length - ((length - 1) - start); //updating length
		} 
		
		else if(start == 0) //special case because firstC instance variable would be changing
		{   
			for(int i = 0; i < end + 1; i++) //traversing through to find end item
			{   
				if(i == end) 
				{ 
					endNode = currNode;   
					endNode.prev = firstC.prev;
					firstC = endNode;   
					length = length - (end - start); 
					
				} 
				currNode = currNode.next; 
			} 
		}
		else 
		{  
			for(int i = 0; i < end + 1; i++) 
			{   
				if(i == start - 1) 
				{  
					startNode = currNode;
				} 
				if(i == end) 
				{ 
					endNode = currNode; 
					startNode.next = endNode; 
					endNode.prev = startNode;
					length = length - (end - start);
				} 
				currNode = currNode.next; 
				
			} 
		
		} 
		return this;
	} 
	
	//traverse through the list just once until you find the start and end 
	//Once currNode = start, set the currNode.next to the end node

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).  If "index"
	// is the last character in the MyStringBuilder, go backward in the list in
	// order to make this operation faster (since the last character is simply
	// the previous of the first character)
	// Be careful for special cases!
	 
	public MyStringBuilder deleteCharAt(int index)
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
			for(int i = 0; i < length; i++) 
			{  
				if(i == index) 
				{  
					
					currNode.prev.next = currNode.next; 
					currNode.next.prev = currNode.prev;
					length --; 
					break;
				}
				currNode = currNode.next;
			} 
		} 
		return this;
	}  
	

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{ 
		CNode currNode = firstC;
		for(int i = 0; i < length; i++) 
		{  
			if(currNode.data == str.charAt(0)) 
			{ 
				CNode tempNode = currNode.next; //go to next iteration so it doesn't start nested for loop with same (already checked) index
				
				boolean myBoolean = true;
				for(int j = 1; j < str.length(); j++) 
				{  
					
					if(tempNode.data != str.charAt(j)) 
					{  
						myBoolean = false; 
						break;
					}   
					tempNode = tempNode.next;
				}  
				if(myBoolean == true) 
				{ 
					return i; //will return starting index if loop completes without breaking(string has been found) 
				}  
			} 
			currNode = currNode.next; 
		} 
		return -1; //unable to find that string
	}  

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
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
			for(int i = 1; i < str.length(); i++) 
			{ 
				newNode = new CNode(str.charAt(i)); 
				currstringNode.next = newNode; 
				newNode.prev = currstringNode;
				currstringNode = newNode;  
			}   
			newNode.next = nextNode; //connecting it to the beginning of the string builder 
			nextNode.prev = newNode; 
			length = length + str.length();	 
		}   

		else 
		{ 
			CNode currNode = firstC; 
			CNode tempNode = currNode; 
			CNode nextNode = currNode;
			for(int i = 1; i < length; i++) 
			{
				currNode = currNode.next; 
				if(i == offset) 
				{  
					nextNode = currNode;
					tempNode = currNode.prev;
					for(int j = 0; j < str.length(); j++) 
					{  
						CNode currstringNode = new CNode(str.charAt(j));  
						tempNode.next = currstringNode; 
						currstringNode.prev = tempNode; 
						tempNode = currstringNode;
					}
				}  
			}  
			tempNode.next = nextNode; //connects it to the rest of the string builder(the previous node at offset)
			nextNode.prev = tempNode;
			length = length + str.length();
		} 
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	 public MyStringBuilder insert(int offset, char c)
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
			CNode currcharNode = new CNode(c);  
			CNode currNode = firstC;  
			CNode nextNode = currNode;
			for(int i = 1; i < length; i++) 
			{
				currNode = currNode.next; 
				if(i == offset) 
				{  
					nextNode = currNode;
					currcharNode.next = nextNode; 
					nextNode.prev = currcharNode; 
					currcharNode.prev = currNode.prev;
				}
			}  
			length = length ++;
		} 
		return this;
	}  

	//Return the length of the current MyStringBuilder
	public int length()
	{ 
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list. 
	
	//this method has been commented out in the main-kept returning an NullPointerException error, couldn't figure out which next field was null
	 public MyStringBuilder replace(int start, int end, String str) 
	{ 
		CNode currNode = firstC; 
		//CNode startNode = new CNode(charAt(start)); 
		//CNode endNode = new CNode(charAt(end));
		if(start < 0 || end <= start) 
		{  
			return null;
		} 
		else if(end > length - 1) 
		{ 
			for(int i = 0; i < length; i++) 
			{   
				if(i == start - 1) 
				{   
					for(int j = 0; j < str.length(); j++) 
					{	
						CNode newNode = new CNode(str.charAt(j));  
						currNode.next = newNode ;  
						newNode.prev = currNode;  
						currNode = newNode;
					} 
					currNode.next = firstC; //removes the nodes
					firstC.prev = currNode; //removes the nodes
					length = length + (end - (length - 1)); //need to update the length since inserting past end of the string builder
				}  
				currNode = currNode.next; 
			}  
		}  
		
		else 
		{  
			CNode tempNode = new CNode(' ');
			for(int i = 0; i < length; i++) 
			{  
				if(i == start) 
				{ 	
					currNode = currNode.prev;
					for(int j = 0; j < str.length(); j++) 
					{
						tempNode = currNode; 
						currNode = currNode.next;
						CNode newNode = new CNode(str.charAt(j));   
						tempNode.next = newNode; 
						newNode.prev = tempNode; 
						tempNode = newNode;	
					}  
				}  
				if(i == end) 
				{  
					tempNode.next = currNode; //removes the nodes 
					currNode.prev = tempNode; 
					break;
				}
				currNode = currNode.next;
			} 
		}  
		return this;
	}
		 

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder.  For this method
	// you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	
	
	public String substring(int start, int end)
	{ 
		char[] s = new char[end - start];  
		if (end < start)  // special case for empty String
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
		// Iterate through remainder of the String, creating a new
		// node at the end of the list for each character.  Note
		// how the nodes are being linked and the current reference
		// being moved down the list. 
			int j = 0;
			for (int i = start; i < end; i++)
			{
				s[j] = charAt(i); //using charAt method
				j ++;
			}
		} 
		return new String(s);
		
	}  

	// Return as a String the reverse of the contents of the MyStringBuilder.  Note
	// that this does NOT change the MyStringBuilder in any way.  See substring()
	// above for the basic approach.
	public String revString()
	{  
		char [] c  = new char[length];
		int i = 0; 
		CNode currNode = firstC.prev; 
		while(i < length) 
		{  
			c[i] = currNode.data;  
			currNode = currNode.prev;
			i++; 
		} 
		return new String(c);
	} 
	

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data, next and prev fields directly.
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
	
	//Extra credit method: returns an array of char values which removes every other character in the string builder instance
	/* public String removeEveryOther()  
	{  
		char [] c = new char[length/2];
		CNode currNode = firstC;
		for(int i = 0; i < length/2; i++) 
		{  
			c[i] = currNode.data; 
			currNode = currNode.next.next;
		} 
		return new String(c);
	}  */
}
