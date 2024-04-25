class Arc {
	public int from;
	public int to;
	public int weight;

	public Arc(int from, int to, int weight) { 
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Arc copy() {
		return new Arc(this.from, this.to, this.weight);
	}
}

class Node {
	// Add your code here
	public Node next; //ΕΠΟΜΕΝΟΣ ΚΟΜΒΟΣ
    public Node prev;//ΠΡΟΗΓΟΥΜΕΝΟΣ ΚΟΜΒΟΣ
	public Arc arc;//ΑΚΜΗ (ΠΕΡΙΕΧΟΜΕΝΑ ΚΟΜΒΟΥ)

	public Node(Arc arc) { //CONSTRUCTOR - ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΤΗΣ NODE
		this.arc = arc;
	}
	
	public void linktoNext(Node node) { //ΜΕΘΟΔΟΣ ΣΥΝΔΕΣΗΣ ΚΟΜΒΟΥ ΜΕ ΤΟΝ ΕΠΟΜΕΝΟ ΤΟΥ
	  this.next = node;
    }
    
    public void linktoPrev(Node node) { //ΜΕΘΟΔΟΣ ΣΥΝΔΕΣΗΣ ΚΟΜΒΟΥ ΜΕ ΤΟΝ ΠΡΟΗΓΟΥΜΕΝΟ ΤΟΥ
	  this.prev = node;
    }
}

class ArcList {// ΔΙΠΛΆ ΣΥΝΔΕΔΕΜΈΝΗ ΛΊΣΤΑ 
//(ΛΟΓΩ ΣΥΧΝΗΣ ΧΡΗΣΗΣ ΚΟΜΒΟΥ ΟΥΡΑΣ, ΕΥΚΟΛΗΣ ΠΡΟΣΒΑΣΗΣ ΣΕ ΠΡΟΗΓΟΥΜΕΝΟ ΚΟΜΒΟ ΚΑΙ ΕΥΚΟΛΟΤΕΡΗΣ ΠΡΟΣΒΑΣΗΣ ΤΩΝ ΤΕΛΕΥΤΑΙΩΝ ΚΟΜΒΩΝ ΜΙΑΣ ΛΙΣΤΑΣ Κ.Λ.Π.)

		private Node head; //ΚΕΦΑΛΉ ΤΗΣ ΛΙΣΤΑΣ
	    private Node tail; //ΟΥΡΆ ΤΗΣ ΛΙΣΤΑΣ

		public ArcList() { //CONSTRUCTOR - ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΤΗΣ ARCLIST
		  // Your code here
		  head=new Node(null); //ΑΡΧΙΚΟΠΟΊΗΣΗ ΚΟΜΒΩΝ
		  tail=new Node(null);
		  head.linktoNext(tail); //ΔΙΠΛΉ ΣΎΝΔΕΣΗ ΚΌΜΒΩΝ
		  tail.linktoPrev(head);
		
		}
		

		public int size() { //ΜΕΘΟΔΟΣ ΕΥΡΕΣΗΣ ΜΕΓΕΘΟΥΣ ΛΙΣΤΑΣ
			// your code here
		   int i=0; //ΜΕΤΑΒΛΗΤΉ ΜΕΤΡΗΤΉΣ
		   Node current = new Node(null);
		   current = head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΕΠΟΜΕΝΟΥ ΤΗΣ ΚΕΦΑΛΗΣ ΚΟΜΒΟΥ ΣΤΗΝ CURRENT
		   while(current!=tail){ //ΟΣΟ Η CURRENT ΔΕΝ ΕΧΕΙ ΦΤΑΣΕΙ ΣΤΟ ΤΕΛΟΣ ΤΗΣ ΛΙΣΤΑΣ
		     current = current.next; //ΜΕΤΑΤΟΠΙΣΗ ΣΤΟΝ ΕΠΟΜΕΝΟ ΚΟΜΒΟ
		     i=i+1; //ΑΥΞΗΣΗ ΜΕΤΡΗΤΗ ΚΑΤΑ 1 ΚΑΘΕ ΦΟΡΑ ΠΟΥ ΠΕΡΝΑ ΑΠΟ ΤΟΝ ΚΑΘΕ ΚΟΜΒΟ ΤΗΣ ΛΙΣΤΑΣ
	       }
	        
			return i; //ΕΠΙΣΤΡΟΦΗ ΜΕΤΡΗΤΗ - ΑΡΙΘΜΗΤΙΚΟΥ ΜΕΓΕΘΟΥΣ ΛΙΣΤΑΣ
		}

		public int insert(Arc arc) {//ΕΙΣΑΓΩΓΗ ΚΟΜΒΟΥ ΣΤΟ ΤΕΛΟΣ ΤΗΣ ΛΙΣΤΑΣ
		// your code here
		if(arc!=null) { //ΚΑΝΕΙ ΕΙΣΑΓΩΓΗ ΜΟΝΟ ΟΤΑΝ ΔΙΝΕΤΑΙ ΕΓΚΥΡΗ ΑΚΜΗ ΓΙΑ ΕΙΣΑΓΩΓΗ
		   Node current = new Node(null);
		   current = tail; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΟΥΡΑΣ ΣΤΗΝ CURRENT
		   Node tmp = new Node(arc.copy());//ΑΡΧΙΚΟΠΟΙΗΣΗ ΑΝΤΙΓΡΑΦΟΥ ΤΗΣ ΑΚΜΗΣ ΠΟΥ ΔΕΧΕΤΑΙ Η ΜΕΘΟΔΟΣ ΣΤΗΝ TMP 
	           
	       tmp.linktoPrev(current.prev);
	       tmp.linktoNext(current);  //ΕΙΣΑΓΩΓΗ ΚΟΜΒΟΥ ΠΡΙΝ ΤΟΝ CURRENT/ΟΥΡΑ
	       
	       current.prev.linktoNext(tmp);
	       tmp.next.linktoPrev(tmp);
		  
		 }
			
			return this.size(); //ΕΠΙΣΤΡΟΦΗ ΝΕΟΥ ΜΕΓΕΘΟΥΣ ΛΙΣΤΑΣ
		}

		public Arc removeFirst() { //ΑΦΑΙΡΕΣΗ ΤΟΥ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΤΗΣ ΛΙΣΤΑΣ
			// your code here
		    
		    Node node = new Node(null);
		    node = head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΣΤΗΝ NODE
		    if(node==tail) {return null;} //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
			
		    node.prev.linktoNext(node.next);
		    node.next.linktoPrev(node.prev); //ΑΦΑΙΡΕΣΗ ΤΟΥ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΤΗΣ ΛΙΣΤΑΣ
		    
		    node.prev=null; //ΚΑΤΑΡΓΗΣΗ ΤΩΝ ΔΕΙΚΤΩΝ PREV ΚΑΙ NEXT ΤΗΣ NODE
		    node.next=null;

			return node.arc; //ΕΠΙΣΤΡΟΦΗ ΤΗΣ ΑΚΜΗΣ ΠΟΥ ΠΕΡΙΕΧΕΤΑΙ ΣΤΗΝ NODE
			
		}
		

		public Arc remove(int from, int to) { //ΑΦΑΙΡΕΣΗ ΚΟΜΒΟΥ ΜΕ ΔΕΔΟΜΕΝΑ ΚΛΕΙΔΙΑ
			// your code here

		     Node current = new Node(null);
		     current = head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΣΤΗΝ CURRENT
			 if(current==tail){return null;}//ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
		 
		     while ((((current.arc).from)!=from) //ΑΝΑΖΗΤΗΣΗ ΚΟΜΒΟΥ ΜΕ ΤΑ ΔΕΔΟΜΕΝΑ TO ΚΑΙ FROM
		     || (((current.arc).to)!=to)){ //ΕΠΑΝΑΛΗΨΗ ΟΣΟ Ο ΠΡΩΤΟΣ ΚΟΜΒΟΣ ΜΕ ΤΑ ΣΤΟΙΧΕΙΑ ΑΥΤΑ ΔΕΝ ΒΡΙΣΚΕΤΑΙ
		        
		      if(current.next==tail)
		          {return null;}   //ΕΠΙΣΤΡΕΦΕΙ NULL ΑΝ ΔΕΝ ΥΠΑΡΧΕΙ ΚΑΘΟΛΟΥ ΤΕΤΟΙΟΣ ΚΟΜΒΟΣ ΣΤΗΝ ΛΙΣΤΑ
		        
		       current=current.next;
			 }
		    
		       
		     current.prev.linktoNext(current.next); //ΑΦΑΙΡΕΣΗ ΤΟΥ ΚΟΜΒΟΥ ΑΠΟ ΤΗ ΛΙΣΤΑ
		     current.next.linktoPrev(current.prev);
		    
		      current.prev=null;   //ΚΑΤΑΡΓΗΣΗ ΤΩΝ ΔΕΙΚΤΩΝ PREV ΚΑΙ NEXT ΤΗΣ CURRENT
		      current.next=null;

			return current.arc; //ΕΠΙΣΤΡΟΦΗ ΤΗΣ ΑΚΜΗΣ ΠΟΥ ΠΕΡΙΕΧΕΤΑΙ ΣΗΝ CURRENT
			
		}
		

		public Arc arc(int from, int to) { //ΕΥΡΕΣΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΑΝΤΙΓΡΑΦΟΥ ΚΟΜΒΟΥ ΜΕ ΔΕΔΟΜΕΝΑ ΚΛΕΙΔΙΑ
			// your code here
		     
		     Node current = new Node(null);
		     current = head.next;
			 if(current==tail){return null;}//ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
			
		     while ((((current.arc).from)!=from) //ΑΝΑΖΗΤΗΣΗ ΚΟΜΒΟΥ ΜΕ ΤΑ ΔΕΔΟΜΕΝΑ TO ΚΑΙ FROM
		     || (((current.arc).to)!=to)){ //ΕΠΑΝΑΛΗΨΗ ΟΣΟ Ο ΠΡΩΤΟΣ ΚΟΜΒΟΣ ΜΕ ΤΑ ΣΤΟΙΧΕΙΑ ΑΥΤΑ ΔΕΝ ΒΡΙΣΚΕΤΑΙ
				 
		        if(current.next==tail)
		          {return null;} //ΕΠΙΣΤΡΕΦΕΙ NULL ΑΝ ΔΕΝ ΥΠΑΡΧΕΙ ΚΑΘΟΛΟΥ ΤΕΤΟΙΟΣ ΚΟΜΒΟΣ ΣΤΗΝ ΛΙΣΤΑ
		        
		       current=current.next;}
		       
		     return current.arc.copy(); //ΕΠΙΣΤΡΟΦΗ ΤΗΣ ΑΚΜΗΣ ΠΟΥ ΠΕΡΙΕΧΕΤΑΙ ΕΝ ΤΕΛΕΙ ΣΤΗΝ CURRENT
		     
		}

		public ArcList arcWeightsIn(int lb, int ub) { //ΚΑΤΑΣΚΕΥΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΤΙΣ ΑΚΜΕΣ ΣΤΟ ΔΕΔΟΜΕΝΟ ΕΥΡΟΣ ΒΑΡΟΥΣ
			// your code here
		    Node current = new Node(null);
			current = head.next;
			if(current==tail){return null;} //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
		    ArcList arc1= new ArcList(); //ΚΑΤΑΣΚΕΥΑΖΕΤΑΙ ΚΑΙΝΟΥΡΓΙΑ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
		   
		   
		   while(current!=tail){ //ΕΠΑΝΑΛΗΨΗ ΟΣΟ Ο ΚΟΜΒΟΣ ΔΕΙΚΤΗΣ ΔΕΝ ΕΧΕΙ ΦΤΑΣΕΙ ΣΤΟ ΤΕΛΟΣ ΤΗΣ ΛΙΣΤΑΣ
		    if((current.arc.weight>=lb) && 
		    (current.arc.weight<=ub))
		    {arc1.insert(current.arc.copy());} //ΕΙΣΑΓΩΓΗ ΑΝΤΙΓΡΆΦΩΝ ΤΩΝ ΑΚΜΩΝ ΜΕ ΒΑΡΟΣ ΣΤΑ ΔΕΔΟΜΕΝΑ ΟΡΙΑ, ΣΤΗΝ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
		     
		    current = current.next;  //
	       }
	       
	       return arc1; //ΕΠΙΣΤΡΟΦΗ ΤΗΣ ΒΟΗΘΗΤΙΚΗΣ ΛΙΣΤΑΣ
		}

		public ArcList heaviestArcs(int k) {  //ΚΑΤΑΣΚΕΥΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΤΗΣ k ΒΑΡΥΤΕΡΕΣ ΑΚΜΕΣ
		  // your code here
			int i;//ΜΕΤΑΒΛΗΤΗ ΜΕΤΡΗΤΗΣ ΒΗΜΑΤΩΝ
		    int s;//ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΚΡΑΤΑ ΤΟ ΜΕΓΕΘΟΣ ΤΗΣ ΛΙΣΤΑΣ
		    int maxw;//ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΚΡΑΤΑ ΤΟ ΜΕΓΑΛΥΤΕΡΟ ΒΑΡΟΣ ΑΠΟ ΟΛΕΣ ΤΙΣ ΑΚΜΕΣ ΓΙΑ ΤΙΣ ΟΠΟΙΕΣ ΕΧΕΙ ΓΙΝΕΙ ΣΥΓΚΡΙΣΗ
		    
		    s=this.size(); //ΑΡΧΙΚΟΠΟΙΗΣΗ ΜΕΓΕΘΟΥΣ ΛΙΣΤΑΣ
		    
			Node current = new Node(null);
			current = head.next;
		    if(current==tail){return null;}  //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
			
			if(k<=0){return null;} //ΑΝ ΔΙΝΕΤΑΙ ΑΡΝΗΤΙΚΟ Ή ΜΗΔΕΝΙΚΟ k, ΤΟΤΕ ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ
			
			if(k>=s){k=s;} // ΑΝ ΤΟ k ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΟ ΤΟΥ ΜΕΓΕΘΟΥΣ ΤΗΣ ΛΙΣΤΑΣ, ΤΟΤΕ ΕΞΙΣΩΝΕΤΑΙ ΜΕ ΤΟ ΜΕΓΕΘΟΣ ΤΗΣ ΛΙΣΤΑΣ
		    
			ArcList arc1= new ArcList(); //ΚΑΤΑΣΚΕΥΗ ΒΟΗΘΗΤΙΚΩΝ ΛΙΣΤΩΝ
			ArcList arc2= new ArcList();
		
			while(current!=tail) {
				arc1.insert(current.arc.copy()); //ΕΙΣΑΓΩΓΗ ΑΝΤΙΓΡΑΦΩΝ ΟΛΩΝ ΤΩΝ ΑΚΜΩΝ ΣΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
				current=current.next;
			}
		
			 current = arc1.head.next;
			 Node  max = new Node(null); // ΔΕΙΚΤΗΣ ΤΟΥ ΚΟΜΒΟΥ ΜΕ ΤΗ ΒΑΡΥΤΕΡΗ ΑΚΜΗ
		
		    
		    for(i=0;i<k;i++){ // Η ΕΜΦΩΛΕΥΜΕΝΗ ΔΙΑΔΙΚΑΣΙΑ ΕΚΤΕΛΕΙΤΑΙ k ΦΟΡΕΣ
		        current = arc1.head.next; //ΜΕΤΑΤΟΠΙΣΗ ΤΟΥ ΔΕΙΚΤΗ current ΣΤΗΝ ΑΡΧΗ ΤΗΣ ΒΟΗΘΗΤΙΚΗΣ ΛΙΣΤΑΣ
			    max = current;
			    maxw=current.arc.weight; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΤΟΥ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΚΑΙ ΤΟΥ ΑΝΤΙΣΤΟΙΧΟΥ ΒΑΡΟΥΣ ΤΗΣ ΑΚΜΗΣ ΤΟΥ ΩΣ ΤΟ ΜΕΓΑΛΥΤΕΡΟ
			    
			    while(current!=arc1.tail) {
			    	if(current.arc.weight>maxw) { //ΕΝΩ ΠΡΟΣΠΕΛΑΥΝΕΤΑΙ Η ΛΙΣΤΑ, ΑΝ Η ΑΚΜΗ ΕΝΟΣ ΚΟΜΒΟΥ ΕΙΝΑΙ ΒΑΡΥΤΕΡΗ ΑΠΟ ΤΗΝ ΑΚΜΗ ΤΗΣ ΜΑΧ
			    		max=current;			//, ΤΟΤΕ Ο ΔΕΙΚΤΗΣ ΜΑΧ ΔΕΙΧΝΕΙ ΣΕ ΑΥΤΗ ΚΑΙ Η ΜΑΧW ΠΑΙΡΝΕΙ ΓΙΑ ΤΙΜΗ ΤΟ ΒΑΡΟΣ ΤΗΣ
			    		maxw= current.arc.weight;
			    	}
			    	current=current.next;
			    } 
				 
				arc2.insert(max.arc.copy()); //ΜΕΤΑ ΑΝΤΙΓΡΑΦΟ ΤΗΣ ΑΚΜΗΣ ΤΟΥ ΚΟΜΒΟΥ ΣΤΟΝ ΟΠΟΙΟ ΔΕΙΧΝΕΙ Η ΜΑΧ, ΕΙΣΑΓΕΤΑΙ ΣΤΗ ΔΕΥΤΕΡΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ (ΛΟΓΩ ΤΟΥ ΟΤΙ ΟΙ ΑΚΜΕΣ ΒΡΙΣΚΟΝΤΑΙ ΜΕ ΤΗ ΣΕΙΡΑ ΑΠΟ ΤΗ
				//ΜΕΓΑΛΥΤΕΡΗ ΣΕ ΒΑΡΟΣ ΠΡΟΣ ΤΗΝ ΜΙΚΡΟΤΕΡΗ ΚΑΙ ΚΑΘΕ ΦΟΡΑ ΕΙΣΑΓΩΝΤΑΙ ΣΤΟ ΤΕΛΟΣ ΤΗΣ ΚΑΙΝΟΥΡΓΙΑΣ ΛΙΣΤΑΣ, ΘΑ ΕΠΙΣΤΡΕΦΕΙ ΜΙΑ ΚΑΙΝΟΥΡΓΙΑ ΛΙΣΤΑ ΜΕ ΤΙΣ 
				//Κ ΜΕΓΑΛΥΤΕΡΕΣ ΑΚΜΕΣ ΤΑΞΙΝΟΜΗΜΕΝΕΣ ΑΠΟ ΤΗ ΜΕΓΑΛΥΤΕΡΗ ΠΡΟΣ ΤΗΝ ΜΙΚΡΟΤΕΡΗ ΣΕ ΒΑΡΟΣ)
				    	  
				max.prev.linktoNext(max.next); //ΥΣΤΕΡΑ ΔΙΑΓΡΑΦΕΤΑΙ ΑΠΟ ΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΕΤΣΙ ΩΣΤΕ ΝΑ ΜΗΝ ΞΑΝΑΛΗΦΘΕΙ ΥΠΟΨΙΝ ΚΑΤΑ ΤΗΝ ΕΠΟΜΕΝΗ ΕΥΡΕΣΗ ΤΟΥ ΜΑΧ
				max.next.linktoPrev(max.prev);
				    
				max.prev=null;  //ΚΑΤΑΡΓΗΣΗ ΤΩΝ ΔΕΙΚΤΩΝ PREV ΚΑΙ NEXT ΤΗΣ ΜΑΧ
				max.next=null;
	          
	        }
		
			return arc2; //ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΤΗΣ k ΒΑΡΥΤΕΡΕΣ ΑΚΜΕΣ
		}
		
	  private int HeaviestInfluence(){ //ΕΥΡΕΣΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΤΟΥ to, ΠΟΥ ΕΧΕΙ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ
		  // your code here
			 int maxpoint; //ΜΕΤΑΒΛΗΤΗ ΜΕ ΤΟ to, ΜΕ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ
			 int max; //ΜΕΤΑΒΛΗΤΗ ΜΕ ΤΟ ΒΑΡΟΣ ΤΟΥ ΒΑΡΥΤΕΡΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ
			 int sumtmp; //ΒΟΗΘΗΤΙΚΗ ΜΕΤΑΒΛΗΤΗ ΜΕΤΡΗΣΗΣ ΤΟΥ ΣΥΝΟΛΙΚΟΥ ΑΘΡΟΙΣΜΑΤΟΣ ΤΟΥ ΚΑΘΕ to , ΓΙΑ ΝΑ ΒΡΕΘΕΙ ΤΟ ΒΑΡΟΣ ΤΟΥ ΚΑΘΕ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ
			 int s1; //ΒΟΗΘΗΤΙΚΗ ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΚΡΑΤΑ ΤΟ to ΓΙΑ ΚΑΘΕ ΠΡΟΣΠΕΛΑΣΗ, ΓΙΑ ΤΗΝ ΕΥΡΕΣΗ ΤΟΥ ΒΑΡΟΥΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΤΟΥ
			 
			 
			 Node current1 = new Node(null);
			 current1 = head.next;
			 if(current1==tail) {return 0;} //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ (ΔΕΝ ΘΑ ΓΙΝΕΙ ΠΟΤΕ Ο ΕΛΕΓΧΟΣ ΛΟΓΩ ΕΠΙΠΛΕΟΝ ΕΛΕΓΧΟΥ ΣΤΗΝ topInfluencers
		
			 Node current2 = new Node(null);
			 Node tmp = new Node(null);
		         
		         ArcList arc1 = new ArcList(); //ΚΑΤΑΣΚΕΥΗ ΒΟΗΘΗΤΙΚΗΣ ΛΙΣΤΑΣ
		         
		         while(current1!=tail){
		           arc1.insert(current1.arc.copy());
					current1=current1.next;				//ΕΙΣΑΓΩΓΗ ΑΝΤΙΓΡΑΦΩΝ ΟΛΩΝ ΤΩΝ ΑΚΜΩΝ ΣΤΗΝ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
		         }
		         
		         current1 = arc1.head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΜΒΩΝ-ΔΕΙΚΤΩΝ ΣΤΗΝ ΑΡΧΗ ΤΗΣ ΒΟΗΘΗΤΙΚΗΣ ΛΙΣΤΑΣ
		         current2=arc1.head.next;
		         sumtmp=0; //ΑΡΧΙΚΟΠΟΙΗΣΗ sumtmp
		         s1= current1.arc.to; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΤΟΥ ΠΡΩΤΟΥ to ΣΤΟ s1
		          while(current2!=arc1.tail){
		            if(current2.arc.to==s1){
		             sumtmp=sumtmp+current2.arc.weight;
		             tmp=current2.prev; //Ο tmp ΚΡΑΤΑ ΤΗ ΘΕΣΗ ΤΟΥ ΠΡΟΗΓΟΥΜΕΝΟΥ ΤΟΥ ΚΟΜΒΟΥ ΠΟΥ ΘΑ ΑΦΑΙΡΕΘΕΙ
		             current2.prev.linktoNext(current2.next); //ΕΥΡΕΣΗ ΣΥΝΟΛΙΚΟΥ ΑΘΡΟΙΣΜΑΤΟΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ 
		             current2.next.linktoPrev(current2.prev);//ΚΑΙ ΑΦΑΙΡΕΣΗ ΚΑΘΕ ΚΟΜΒΟΥ ΤΟΥ ΟΠΟΙΟΥ ΤΟ ΒΑΡΟΣ ΤΗΣ ΑΚΜΗΣ ΧΡΗΣΙΜΟΠΟΙΗΤΑΙ ΑΠΟ ΤΗ ΛΙΣΤΑ
			    
		             current2.prev=null;
		             current2.next=null;
		             current2= tmp; //ΜΕΤΑ ΕΠΙΣΤΡΕΦΕΙ ΤΗ ΘΕΣΗ ΣΤΗΝ current2 ΚΑΙ ΕΤΣΙ ΟΤΑΝ ΠΑΕΙ ΣΤΟ ΕΠΟΜΕΝΟ, ΚΑΤΑΛΗΓΕΙ 
										//ΣΤΟΝ ΕΠΟΜΕΝΟ ΚΟΜΒΟ ΑΠΟ ΑΥΤΟΝ ΠΟΥ ΑΦΑΙΡΕΘΗΚΕ
		            }
		            current2=current2.next;
		          }

		            max=sumtmp; //ΑΡΧΙΚΟΠΟΙΗΣΗ max ΚΑΙ maxpoint ΜΕ ΤΑ ΣΤΟΙΧΕΙΑ ΤΟΥ ΠΡΩΤΟΥ to, ΕΤΣΙ ΩΣΤΕ 
					maxpoint=s1;	//ΝΑ ΜΠΟΡΟΥΝ ΝΑ ΕΧΟΥΝ ΜΙΑ ΠΡΩΤΗ ΤΙΜΗ ΚΑΙ ΝΑ ΜΠΟΡΕΙ ΝΑ ΓΙΝΕΙ Ο ΕΛΕΓΧΟΣ ΜΕΤΑ
		         
		         current1=arc1.head.next; 
		        while(current1!=arc1.tail){ //Η ΔΙΑΔΙΚΑΣΙΑ ΟΥΣΙΑΣΤΙΚΑ ΞΕΚΙΝΑ ΜΕ ΤΟΝ ΕΠΟΜΕΝΟ ΤΟΥ ΠΡΩΤΟΥ to 
		          sumtmp=0;						//ΠΟΥ ΑΡΧΙΚΟΠΟΙΗΘΗΚΕ, ΑΦΟΥ ΟΙ ΑΚΜΕΣ ΤΟΥ ΕΧΟΥΝ ΑΦΑΙΡΕΘEI
		          s1= current1.arc.to;
		          while(current2!=arc1.tail){
		            if(current2.arc.to==s1){
		             sumtmp=sumtmp+current2.arc.weight;
		             tmp=current2.prev; //Ο tmp ΚΡΑΤΑ ΤΗ ΘΕΣΗ ΤΟΥ ΠΡΟΗΓΟΥΜΕΝΟΥ ΤΟΥ ΚΟΜΒΟΥ ΠΟΥ ΘΑ ΑΦΑΙΡΕΘΕΙ
		             current2.prev.linktoNext(current2.next); //ΕΥΡΕΣΗ ΣΥΝΟΛΙΚΟΥ ΑΘΡΟΙΣΜΑΤΟΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ 
		             current2.next.linktoPrev(current2.prev);//ΚΑΙ ΑΦΑΑΙΡΕΣΗ ΚΑΘΕ ΚΟΜΒΟΥ ΤΟΥ ΟΠΟΙΟΥ ΤΟ ΒΑΡΟΣ ΤΗΣ ΑΚΜΗΣ ΧΡΗΣΙΜΟΠΟΙΗΤΑΙ ΑΠΟ ΤΗ ΛΙΣΤΑ
			    
		             current2.prev=null;
		             current2.next=null;
		             current2= tmp;   //ΜΕΤΑ ΕΠΙΣΤΡΕΦΕΙ ΤΗ ΘΕΣΗ ΣΤΗΝ current2 ΚΑΙ ΕΤΣΙ ΟΤΑΝ ΠΑΕΙ ΣΤΟ ΕΠΟΜΕΝΟ, ΚΑΤΑΛΗΓΕΙ 
										//ΣΤΟΝ ΕΠΟΜΕΝΟ ΚΟΜΒΟ ΑΠΟ ΑΥΤΟΝ ΠΟΥ ΑΦΑΙΡΕΘΗΚΕ
		            }
		            current2=current2.next;
		          }
		          if(sumtmp>max){ //ΑΝ ΤΟ ΑΘΡΟΙΣΜΑ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΟ ΤΟΥ max, ΤΟΤΕ ΓΙΝΕΤΑΙ 
										//ΑΥΤΟ ΤΟ max ΚΑΙ ΤΟ to ΤΟΥ ΓΙΝΕΤΑΙ ΤΟ maxpoint
		            max=sumtmp;
		            maxpoint=s1;
		          }
		          current1=arc1.head.next;
		       }
		         
		         
		      
		  return maxpoint; //ΕΠΙΣΤΡΟΦΗ ΤΟΥ to, ΠΟΥ ΕΧΕΙ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ
	}

		public ArcList leastInfluence(int sum) { //ΚΑΤΑΣΚΕΥΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΤΙΣ ΑΚΜΕΣ ΤΩΝ to, ΜΕ ΥΠΟΓΡΑΦΗΜΑΤΑ ΕΠΙΡΡΟΗΣ ΒΑΡΥΤΕΡΑ ΤΟΥ sum
			// your code here
		  int sumtmp=0; //ΒΟΗΘΗΤΙΚΗ ΜΕΤΑΒΛΗΤΗ ΜΕΤΡΗΣΗΣ, ΓΙΑ ΝΑ ΒΡΕΘΕΙ ΤΟ ΒΑΡΟΣ ΤΟΥ ΚΑΘΕ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ
	      int s1=0; //ΒΟΗΘΗΤΙΚΗ ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΚΡΑΤΑ ΤΟ to ΓΙΑ ΚΑΘΕ ΠΡΟΣΠΕΛΑΣΗ, ΓΙΑ ΤΗΝ ΕΥΡΕΣΗ ΤΟΥ ΒΑΡΟΥΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΤΟΥ

		  
		  	 Node current1 = new Node(null);
			 current1 = head.next;
			 if(current1==tail) {return null;} //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
			 Node current2 = new Node(null);
			 Node tmp = new Node(null);
		         
		         ArcList arc1 = new ArcList(); //ΚΑΤΑΣΚΕΥΗ ΒΟΗΘΗΤΙΚΩΝ ΛΙΣΤΩΝ
		         ArcList arc2 = new ArcList();
		         while(current1!=tail){
			           arc1.insert(current1.arc.copy());    //ΕΙΣΑΓΩΓΗ ΑΝΤΙΓΡΑΦΩΝ ΟΛΩΝ ΤΩΝ ΑΚΜΩΝ ΣΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
			           current1=current1.next;
			     }
		         
		        current1=arc1.head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΜΒΩΝ-ΔΕΙΚΤΩΝ ΣΤΗΝ ΑΡΧΗ ΤΗΣ ΒΟΗΘΗΤΙΚΗΣ ΛΙΣΤΑΣ
		        while(current1!=arc1.tail){
		          sumtmp=0;      //ΜΗΔΕΝΙΣΜΟΣ ΤΟΥ sumtmp ΚΑΘΕ ΦΟΡΑ ΓΙΑ ΝΑ ΑΝΑΝΕΩΝΕΤΑΙ ΜΕ ΤΟ ΑΘΡΟΙΣΜΑ ΤΟΥ ΚΑΘΕ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΣΩΣΤΑ
		          s1= current1.arc.to; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΤΟΥ to ΓΙΑ ΤΟ ΟΠΟΙΟ ΘΑ ΒΡΟΥΜΕ ΤΟ ΣΥΝΟΛΙΚΟ ΒΑΡΟΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΤΟΥ, ΚΑΘΕ ΦΟΡΑ
		          current2=arc1.head.next;
		          while(current2!=arc1.tail){
		            if(current2.arc.to==s1){
		             sumtmp=sumtmp+current2.arc.weight;
		              tmp=current2.prev; //Ο tmp ΚΡΑΤΑ ΤΗ ΘΕΣΗ ΤΟΥ ΠΡΟΗΓΟΥΜΕΝΟΥ ΤΟΥ ΚΟΜΒΟΥ ΠΟΥ ΘΑ ΑΦΑΙΡΕΘΕΙ
		              current2.prev.linktoNext(current2.next); //ΕΥΡΕΣΗ ΣΥΝΟΛΙΚΟΥ ΑΘΡΟΙΣΜΑΤΟΣ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ 
		              current2.next.linktoPrev(current2.prev); //ΚΑΙ ΑΦΑΑΙΡΕΣΗ ΚΑΘΕ ΚΟΜΒΟΥ ΤΟΥ ΟΠΟΙΟΥ ΤΟ ΒΑΡΟΣ ΤΗΣ ΑΚΜΗΣ ΧΡΗΣΙΜΟΠΟΙΗΤΑΙ ΑΠΟ ΤΗ ΛΙΣΤΑ
			    
		              current2.prev=null;
		              current2.next=null;
		              current2= tmp; //ΜΕΤΑ ΕΠΙΣΤΡΕΦΕΙ ΤΗ ΘΕΣΗ ΣΤΗΝ current2 ΚΑΙ ΕΤΣΙ ΟΤΑΝ ΠΑΕΙ ΣΤΟ ΕΠΟΜΕΝΟ, ΚΑΤΑΛΗΓΕΙ 
										//ΣΤΟΝ ΕΠΟΜΕΝΟ ΚΟΜΒΟ ΑΠΟ ΑΥΤΟΝ ΠΟΥ ΑΦΑΙΡΕΘΗΚΕ
		            }
		            current2=current2.next;
		          }
		        
		          if(sumtmp>=sum){  
		        	  current2=head.next;         //ΑΝ ΤΟ ΑΘΡΟΙΣΜΑ ΤΟΥ ΥΠΟΓΡΑΦΗΜΑΤΟΣ ΕΠΙΡΡΟΗΣ ΤΟΥ ΕΠΙΛΕΓΜΕΝΟΥ to ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΟ ΤΟΥ sum, ΤΟΤΕ ΓΙΝΕΤΑΙ 
		        	  while(current2!=tail) {   //ΕΙΣΑΓΩΓΗ ΟΣΩΝ ΑΚΜΩΝ ΕΧΟΥΝ to ΙΔΙΟ ΜΕ ΤΟ ΕΠΙΛΕΓΜΕΝΟ(s1) (ΑΠΟ ΤΗΝ ΚΑΝΟΝΙΚΗ ΛΙΣΤΑ ΟΧΙ ΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ, 
		        		  if(current2.arc.to==s1) {   //ΓΙΑΤΙ ΑΠΟ ΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΕΧΟΥΝ ΑΦΑΙΡΕΘΕΙ), ΣΤΗΝ ΔΕΥΤΕΡΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ

		        			  arc2.insert(current2.arc.copy());
		        		  }
		        		current2=current2.next;
		        	  }
		          }
		          current1=arc1.head.next; //ΕΠΙΣΤΡΟΦΗ ΣΤΗΝ ΑΡΧΗ ΤΗΣ ΛΙΣΤΑΣ ΠΡΙΝ ΤΗΝ ΕΠΟΜΕΝΗ ΕΠΑΝΑΛΗΨΗ
		       }
	        
		  return arc2;  //ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΤΙΣ ΑΚΜΕΣ ΤΩΝ to, ΜΕ ΥΠΟΓΡΑΦΗΜΑΤΑ ΕΠΙΡΡΟΗΣ ΒΑΡΥΤΕΡΑ ΤΟΥ sum
		}

		public ArcList topInfluencers(int k) { //ΚΑΤΑΣΚΕΥΗ ΚΑΙ ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΑΝΤΙΓΡΑΦΑ ΤΩΝ ΑΚΜΩΝ ΤΩΝ k ΒΑΡΥΤΕΡΩΝ ΥΠΟΓΡΑΦΗΜΑΤΩΝ ΕΠΙΡΡΟΗΣ
		// your code here
	    int maxpoint; //ΜΕΤΑΒΛΗΤΗ ΜΕ ΤΟ to, ΜΕ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ
	    int i; //ΜΕΤΑΒΛΗΤΗ ΜΕΤΡΗΤΗΣ ΒΗΜΑΤΩΝ
		int s; //ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΚΡΑΤΑ ΤΟ ΜΕΓΕΘΟΣ ΤΗΣ ΛΙΣΤΑΣ

	    s=this.size();
	    
	    Node current1 = new Node(null);
	    current1= head.next;
	    if(current1==tail){return null;} //ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΛΙΣΤΑ ΕΙΝΑΙ ΚΕΝΗ
	    
		
		if(k<=0){return null;} //ΑΝ ΔΙΝΕΤΑΙ ΑΡΝΗΤΙΚΟ Ή ΜΗΔΕΝΙΚΟ k, ΤΟΤΕ ΔΕΝ ΕΚΤΕΛΕΙΤΑΙ Η ΔΙΑΔΙΚΑΣΙΑ
		if(k>=s){k=s;} // ΑΝ ΤΟ k ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΟ ΤΟΥ ΜΕΓΕΘΟΥΣ ΤΗΣ ΛΙΣΤΑΣ, ΤΟΤΕ ΕΞΙΣΩΝΕΤΑΙ ΜΕ ΤΟ ΜΕΓΕΘΟΣ ΤΗΣ ΛΙΣΤΑΣ
	      
	      ArcList arc4 = new ArcList(); //ΚΑΤΑΣΚΕΥΗ ΒΟΗΘΗΤΙΚΩΝ ΛΙΣΤΩΝ
	      ArcList arc5 = new ArcList();
	       
	      
	      while(current1!=tail){
	        arc4.insert(current1.arc.copy());  //ΕΙΣΑΓΩΓΗ ΑΝΤΙΓΡΑΦΩΝ ΟΛΩΝ ΤΩΝ ΑΚΜΩΝ ΣΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
	        current1=current1.next;
	      }

	     Node tmp1 = arc4.head.next; //ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΜΒΟΥ-ΔΕΙΚΤΗ tmp1
		 
		for(i=0;i<k;i++){    // Η ΕΜΦΩΛΕΥΜΕΝΗ ΔΙΑΔΙΚΑΣΙΑ ΕΚΤΕΛΕΙΤΑΙ k ΦΟΡΕΣ
	     
	     maxpoint=arc4.HeaviestInfluence(); //ΕΚΤΕΛΕΣΗ HeaviestInfluence ΓΙΑ ΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ, 
												//ΓΙΑ ΤΗΝ ΕΥΡΕΣΗ ΤΟΥ to ΜΕ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ
	     current1 = arc4.head;
		 current1=current1.next;  //ΑΡΧΙΚΟΠΟΙΗΣΗ ΣΤΗΝ current1 ΤΟΥ ΠΡΩΤΟΥ ΚΟΜΒΟΥ ΤΗΣ arc4
		     while (current1!=arc4.tail){
		      if(current1.arc.to==maxpoint){
		    	  arc5.insert(current1.arc.copy()); //ΑΝ ΤΟ to ΕΝΟΣ arc ΤΑΥΤΙΖΕΤΑΙ ΜΕ ΤΟ to ΜΕ ΤΟ to ΜΕ ΤΟ ΒΑΡΥΤΕΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ ,ΤΟΤΕ
												//ΕΙΣΑΓΕΤΑΙ ΑΝΤΙΓΡΑΦΟ ΤΟΥ arc ΑΥΤΟΥ ΣΤΗΝ ΔΕΥΤΕΡΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ 
												//ΚΑΙ Ο ΚΟΜΒΟΣ ΠΟΥ ΤΟ ΕΧΕΙ, ΑΦΑΙΡΕΙΤΑΙ ΑΠΟ ΤΗΝ ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ
		    	  tmp1=current1.prev; //Ο tmp ΚΡΑΤΑ ΤΗ ΘΕΣΗ ΤΟΥ ΠΡΟΗΓΟΥΜΕΝΟΥ ΤΟΥ ΚΟΜΒΟΥ ΠΟΥ ΘΑ ΑΦΑΙΡΕΘΕΙ
		    	  current1.prev.linktoNext(current1.next);
		      	  current1.next.linktoPrev(current1.prev);
		    
		      	  current1.prev=null;
		      	  current1.next=null;
		      	  current1=tmp1;}    //ΜΕΤΑ ΕΠΙΣΤΡΕΦΕΙ ΤΗ ΘΕΣΗ ΣΤΗΝ current1 ΚΑΙ ΕΤΣΙ ΟΤΑΝ ΠΑΕΙ ΣΤΟ ΕΠΟΜΕΝΟ, ΚΑΤΑΛΗΓΕΙ 
										//ΣΤΟΝ ΕΠΟΜΕΝΟ ΚΟΜΒΟ ΑΠΟ ΑΥΤΟΝ ΠΟΥ ΑΦΑΙΡΕΘΗΚΕ
		      
		     current1=current1.next;}
		    if(arc4.size()<=0) {break;} //Η ΣΥΝΘΗΚΗ ΑΥΤΗ ΕΞΑΣΦΑΛΙΖΕΙ ΟΤΙ ΔΕΝ ΘΑ ΣΥΝΕΧΙΣΤΕΙ Η ΔΙΑΔΙΚΑΣΙΑ ΑΝ Η ΠΡΩΤΗ ΒΟΗΘΗΤΙΚΗ ΛΙΣΤΑ ΕΧΕΙ ΗΔΗ ΑΔΕΙΑΣΕΙ
			                          //(ΛΟΓΩ ΤΟΥ ΟΤΙ ΜΕΤΑ ΑΠΟ ΚΑΘΕ ΕΠΑΝΑΛΗΨΗ ΤΗΣ for, ΑΦΑΙΡΕΙΤΑΙ ΕΝΑ ΟΛΟΚΛΗΡΟ ΥΠΟΓΡΑΦΗΜΑ ΕΠΙΡΡΟΗΣ ΑΠΟ ΑΥΤΗ)
	   }
	   return arc5;  //ΕΠΙΣΤΡΟΦΗ ΛΙΣΤΑΣ ΜΕ ΑΝΤΙΓΡΑΦΑ ΤΩΝ ΑΚΜΩΝ ΤΩΝ k ΒΑΡΥΤΕΡΩΝ ΥΠΟΓΡΑΦΗΜΑΤΩΝ ΕΠΙΡΡΟΗΣ
	  
	 }
	}

