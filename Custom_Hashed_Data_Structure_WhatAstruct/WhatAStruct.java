class Node
{
	// Add your code here
	public int id;
	public int priority;
	public boolean deleted; //PEDIO POU DEIXNEI AN O KOMBOS, EINAI O KOMBOS DIAGRAFHS 'H OXI
	public Node(int id, int priority) { 
		this.id = id;
		this.priority = priority;
		this.deleted=false; //ARXIKOPOIEITAI false GIA OLOUS TOUS KOMBOUS
	}
}  

class WhatAStruct
{//DEUTEROVATHMIA DIEREUNHSH (QUADRATIC PROBING)

		private Node[] node;  //PINAKAS KONVWN
	    private Node noNode;  //KOMVOS DIAFRAFHS (SE AUTON DEIXNOUN OSOI KOMBOI DIAGRAFONTAI
	    private int flag1=0;
	    
	    private boolean isPrime(int n){ //METHODOS POU ELEGXEI AN ENAS ARITHMOS EINAI PRWTOS
	      int j;
	      for(j=2; (j*j<=n); j++){ //ELEGXEI GIA KATHE ARITHMO APO TO 2 KAI META, AN DIAIREI TON DOTHENTA ARITHMO n, KAI AN NAI, EPISTREFEI false
	         if(n%j==0){return (false);} //EKTELEITAI EOS OTOY TO j*j na GINEI >n, GIATI O MEGALYTEROS ARITHMOS 
	      }								//POU MPOREI NA DIAIRESEI TO n KAI DEN DIAIREITAI KAI O IDIOS APO PROHGOUMENOUS TOU ARITHMOUS,
										// EINAI <= THS TETRAGWNIKHS TOU RIZAS
	      return (true);
	    }
			
		public WhatAStruct(int mx) {
			// Add your code here 
		   if(mx<0) {mx=mx*(-1);}
	       int size;
	       size=2*mx;
		   if(mx!=1){
			   size++;
		   }												//SYMFWNA ME TO THEORHMA GIA THN DEYTEROVATHMIA DIEREYNHSH, AN O PINAKAS EINAI TO POLY KATA TO HMISY
								
	       while(isPrime(size)==false){ //GEMATOS (loadFactor<=0.5) KAI TO MEGETHOS TOU EINAI PRWTOS ARITHMOS, TOTE MPOREI NA EISAXTHEI PANTA OPOIODHPOTE NEO KLEIDI
	         size+=1;					//ARA PREPEI NA EXΟYME PINAKA ME MEGETHOS TOYLAXISTON DIPLASIO APO TA STOIHEIA POY THELOYME SIGOYRA NA XORESOUN 
	       }							//KAI TO MEGETHOS AYTO NA EINAI PRVTOS ARITHMOS, ARA THELOYME TON PRWTO ARITHMO POY YPARXEI META TO DIPLASIO TOY mx, POY EINAI PRWTOS.
	       node = new Node[size];       //DHMIOYRGIA TOU PINAKA ME NODES ME TO MEGETHOS size POU BRETHIKE
	       noNode = new Node(-1, -1);    //DHMIOYRGIA KOMBOY DIAGRAFHS - VAZOYME TO PEDIO TOY deleted=true, POY TON XARAKTHRIZEI
	       noNode.deleted=true;
		}
		
		private int hash (int id){
			if (id<0) {id=id*(-1);}    //METHODOS hash, VRISKEI TO hash GIA TO ANTISTOIHO id (AN id<0, TO THETIKOPOIEI GIA TON YPOLOGISMO TOY hash, 
		  return id % this.node.length; //AFOY DEN MPOROYME NA EXOUMEARNHTIKH THESH SE ENAN PINAKA)
		}
		
		public boolean insert(Node n) {
			// Add your code here 
		
		if(n==noNode){return false;}
		
	     int hfirst= this.hash(n.id);    //KRATAEI TO hash GIA TO id TOY KONBOY POY THΕLOYME NA EISAGOYME
	     
	     int count = 0;                 //METRHTHS THESEWN TOY PINAKA POY PROSPELSTHKAN 
	     int h=hfirst;                  //METABLHTH POY KRATA TH THESH STHN OPOIA GINETAI KATHE FORA O ELEGXOS 
	     while(node[h]!=null && count<node.length){ //GINETAI ELEGXOS MEXRI NA VRETHEI KENH THESH STON PINAKA 'H NA PROSPELSTOUN OLES OI THESEIS TOY
	      if (node[h]==noNode){break;}     //SE PERIPTWSH POY O KOMBOS POY THELOYME NA EISAGOYME EIXE PRIN DIAGRAFEI, APLA TON EISAGOYME STHN ANTISTOIXH THESH (GIA AYTO break)
	      if(node[h].id == n.id) {        //SE PERIPTWSH POY PESOYME PANW SE KOMBO ME IDIO id, ME TOY KOMBOY POY THELOYME NA EISAGOYME
	    	  if(flag1==1) {   			//H flag1, DEIXNEI AN VRISKOMASTE SE PERIPTWSH POY KANOYME EISAGWGH TWN STOIXEIWN THS w WhatAStruct MESW THS union, THS w MESW THS diff
										// 'H KANONIKH EISAGVGH KOMBOY
	    		  node[h].priority=node[h].priority+n.priority; //AN EIMASTE STHN PRWTH PERIPTWSH (flag1=1), TOTE STA IDIA STOIXEIA, VAZOYME NA EXOYN 
	    		  return true;                					//priority TO ATHROISMA TWN priority TWN KOMBWN ME KOINO id
																//EPISTREFEI true
	    	  }
	    	  else if(flag1==2) {					//AN EIMASTE STHN PRWTH PERIPTWSH (flag1=2), TOTE STA IDIA STOIXEIA, VAZOYME NA EXOYN 
	    		  int i= node[h].priority - n.priority;  //priority, TH DIAFORA TWN priority TWN KOMBWN ME KOINO id
	    		  if(i<0) {i = i*(-1);}        //KANOYME ENAN ELEGHO ETSI WSTE NA APOKOMISOYME THN APOLYTH TIMH TOY APOTELESMATOS, AFOU THELEI THN DIAFORA METAKSY TWN priority
	    		  node[h].priority =i;
	    		  return true; 				  //EPISTREFEI true
	    	  }
	    	  else {return false;} 			//SE PERIPTWSH POY KANEI APLH EISAGWGH (flag1=0), EPISTREFEI false GIATI DEN MPOROYN NA YPARHOYN STON IDIO PINAKA KOMBOI ME IDIO id
	       }
	      
	      count+=1;							//KATHE FORA POY PROSPELAYNETAI ENA STOIXEIO, O METRHTHS AYKSANETAI KATA 1
	      
	      h=(hfirst+count^2)%node.length;  //EMMESH YLOPOIHSH THS DEYTEROBATHMIAS SYNARTHSHS EPILYSHS SYGKROYSEWVN (F(i) = i^2) ME THN count
										//EKSETAZONTAI SEIRIAKA OI THESEIS hfirst (= this.hash(n.id)) mod TO MEGETHOS TOY PINAKA
	     }
	     if(flag1!=2) {   //SE PERIPTWSH THS union (GIA THN w) 'H KANONIKHS EISAGWGHS APLA EISAGEI TA STOIXEIA GIA TA OPOIA 
			//EHEI VRETHEI KENOS KOMBOS ENTOS TOY PINAKA KAI DEN YPARXOYN HDH SE AUTON. (STHN diff (flag1=2) DEN EIAGEI TA STOIXEIA THS w, POY DEN VRISKONTAI STHN this)
	      if (count < node.length){ //ELEGHEI AN EHOYN PROSPELASTEI OLES OI THESEIS TOY PINAKA (MPOREI NA GINEI TAYTHRONA node[h]==null KAI count>=node.length
	       node [h] = n;           //ESAGWGH KOMBOY STHN KENH THESH
	       return true;				//EPISTREFEI true
	      }
	     }
			return false;   //EPISTREFEI false AN DEN VRETHEI THESH GIA NA EISAXTHEI O KOMBOS 'H DEN EISAXTHEI LOGW diff
		} 
		
		public Node remove() {                 
			// Add your code here 
	      int i=0;
	      boolean empty=true;   //METABLHTH boolean POY DEIXNEI AN O PINAKAS THS DOMHS EINAI ADEIOS 'H OXI
	      int max=-1;			//METABLHTH ME TO MEGALYTERO priority
	      int maxi=-1;			//METABLHTH ME TH THESH TOY KOMBOY ME TOMEGALYTERO priority
	      
	      
	      for(i=0;i<this.node.length; i++){
	        if (this.node[i]!=null && this.node[i]!=noNode ){ //ARXIKOPOIEI TA max KAI maxi ME TA DEDOMENA TOY PRWTOY STOIXEIOY POY THA BREI 
	          empty=false;						//(GIA NA MPOREI NA KSEKINHSEI META H SYGKRISH GIA EYRESH TOY max) KAI KANEI THN empty=false OTAN/AN TO BREI
	          max=this.node[i].priority;
	          maxi=i;
	          break;
	        }
	      }
	      
	      if(!empty){  //AN H DOMH DEN EINAI KENH, KSEKINA THN SYGKRISH
	        
	        for(i=maxi+1;i<this.node.length; i++){
	         if(this.node[i]!=null && this.node[i]!=noNode){ //BRISKEI TO STOIXEIO ME TO MEGALYTERO priority ME SEIRIAKH PROSPELASH TOY PINAKA THS this
	          if(this.node[i].priority>max){     //(KENOI KOMBOI THEOROYNTAI OSOI EINAI null, ALLA KAI OSOI EINAI noNode [YPHRXAN KAI DIAGRAFHKAN])
	            max=this.node[i].priority;
	            maxi=i;
	          }
	         }
	        }
	 
	        Node tmp = node[maxi];         //BOITHITIKOS KOMBOS tmp KRATA TON KOMBO ME TO MEGALYTERO priority, ENW AYTOS O KOMBOS APOMAKRYNETAI APO TON PINAKA
	        node[maxi] = noNode;
	        return tmp;						//EPISTREFETAI O KOMBOS ME TO MEGALYTERO priority
	      }
	      else{  //AN H DOMH EINAI KENH, EPISTREFEI null
			return null;
		  }
		} 
		
		public boolean contains(int id) {
			// Add your code here 
	     int hfirst = hash(id);    //KRATAEI TO hash GIA TO id POY HTELOYME NA EIAGOYME
	     
	     int count=0;				 //METRHTHS THESEWN TOY PINAKA POY PROSPELSTHKAN 
	     int h=hfirst;				//METABLHTH POY KRATA TH THESH STHN OPOIA GINETAI KATHE FORA O ELEGXOS 
	     while(this.node[h]!= null && this.node[h]!=noNode && count<this.node.length)  //GINETAI ELEGXOS MEXRI NA VRETHEI KENH THESH STON PINAKA (me null - ARA DEN EHEI EISAXTHEI O KOMBOS 
	     { 									//ME AYTO TO id, AFOY THA EPREPE NA VRISKETAI SE AYTHN THN KENH THESH)'H NA PROSPELSTOUN OLES OI THESEIS TOY (DEN VRISKETAI MESA 
											//STON PINAKA GIATI DEN XORAGE NA EISAXTHEI APO PRIN) H' NA PESEI PANW SE THESH POY DEIXNEI STON noNode (TO STOIXEIO ME AYTO TO id 
											//HTAN STON PINAKA KAI DIAGRAFHKE)
	       if (this.node[h].id==id){ //EPISTREFEI true AN O KOMBOS ME AYTO TO id VRETHEI
	         return true;
	       }
	      
	       count+=1;					//KATHE FORA POY PROSPELAYNETAI ENA STOIXEIO, O METRHTHS AYKSANETAI KATA 1
	      
	       h = (hfirst+count^2) % this.node.length;   //EMMESH YLOPOIHSH THS DEYTEROBATHMIAS SYNARTHSHS EPILYSHS SYGKROYSEWVN (F(i) = i^2) ME THN count
										//EKSETAZONTAI SEIRIAKA OI THESEIS hfirst (= this.hash(n.id)) mod TO MEGETHOS TOY PINAKA
	       
	     }
	     
			return false;        //EPISTREFEI false AN O KOMBOS ME TO DOSMENO id, DEN VRETHEI
		}
		
		
		
		public WhatAStruct union(WhatAStruct w) {
			// Add your code here 
	     int pl1=0;
	     int pl2=0;
		 int i;
	     
	     
	     for(i=0;i<this.node.length; i++){
		        if (this.node[i]!=null && this.node[i]!=noNode){         //BRISKETAI TO PLITHOS TWN STOIXEIWN STON PINAKA THS this
		          pl1++;
		        }
		 }
	     
	     for(i=0;i<w.node.length; i++){
		        if (w.node[i]!=null && w.node[i]!=noNode){				 //BRISKETAI TO PLITHOS TWN STOIXEIWN STON PINAKA THS w
		          pl2++;
		        }
		 }
	     
	     WhatAStruct str1 = new WhatAStruct(pl1+pl2);                  //KATASKEYAZETAI STIGMIOTYPO WhatAStruct, ME MEGETHOS PINAKA KATALLHLO GIA pl1+pl2 STOIXEIA
	     
	     for(i=0;i<this.node.length;i++){
	          if(this.node[i] != null){
	          str1.insert(new Node(this.node[i].id, this.node[i].priority));  
	          }	
	     } 
	      str1.flag1=1;
						//THELOYME H str1 NA EXEI MESA OLA TA STOIXEIA TWN this KAI w, KAI TA KOINA AYTWN NA EXOYN priority ISO ME TO ATHROISMA TWN IDIWN.
	 //ARA BAZOYME PRWTA OLA TA STOIHEIA THS this (NEOYS KOMBOYS ANTIGRAFA AYTWN) MESA STHN str1 KANONIKA KAI META KANOYME TO flag1=1,ETSI WSTE META STHN insert NA BALEI KANONIKA TA
	 //STOIXEIA THS w (NEOYS KOMBOYS ANTIGRAFA AYTWN) POY DEN VRISKONTAI STHN str1 (DHLADH KAI STHN this) KAI GIA AYTA POY BRISKONTAI APLA NA ALLAKSEI TO priority OPWS PREPEI
	     for(i=0;i<w.node.length;i++){
	      if(w.node[i]!=null){
	    	  str1.insert(new Node(w.node[i].id, w.node[i].priority));
	       }
	      }
	     
	     str1.flag1=0;  //KSANAKANEI TO flag1=0, GIA NA GINETAI META KANONIKH EISAGWGH STOIXEIWN
	     return str1;   //EPISTREFEI TH ZHTOYMENH WhatAStruct
		}	

		
		public WhatAStruct diff(WhatAStruct w) {
			// Add your code here 
	     int i;
	     int pl1=0;
	     
	     for(i=0;i<this.node.length; i++){
		        if (this.node[i]!=null && this.node[i]!=noNode){ //BRISKETAI TO PLITHOS TWN STOIXEIWN STON PINAKA THS this (AFOY H str2 THA EXEI OSA STOIXEIA EXEI KAI H this)
		          pl1++;
		        }
	     }
	     
	     WhatAStruct str2 = new WhatAStruct(pl1);    //KATASKEYAZETAI STIGMIOTYPO WhatAStruct, ME MEGETHOS PINAKA KATALLHLO GIA pl1 STOIXEIA
	     
	     for(i=0;i<this.node.length;i++){
	          if(this.node[i] != null){
	          str2.insert(new Node(this.node[i].id, this.node[i].priority));
	          }
	     }
	     
	     str2.flag1=2;
						//THELOYME H str2 NA EXEI MESA OLA TA STOIXEIA THS this, KAI GIA OSA YPARXOYN KAI STH this KAI STHN w, NA 
				//EXOYN priority ISO ME TH DIAFORA TWN IDIWN id. ARA BAZOYME PRWTA OLA TA STOIXEIA (NEOYS KOMBOYS ANTIGRAFA AYTWN) THS this MESA  
				//STHN str2 KANONIKA KAI META KANOYME TO flag1=2,ETSI WSTE META STHN insert GIA AYTA POY VRISKONTAI KAI STIS DYO, APLA NA ALLAKSEI TO priority OPWS PREPEI
	      
	     for(i=0;i<w.node.length;i++){
	      if(w.node[i]!=null){
	    	  str2.insert(new Node(w.node[i].id, w.node[i].priority));
	       }
	      }
	     
	     str2.flag1=0;		//KSANAKANEI TO flag1=0, GIA NA GINETAI META KANONIKH EISAGWGH STOIXEIWN
		 return str2;     //EPISTREFEI TH ZHTOYMENH WhatAStruct
		}

		public WhatAStruct kbest(int k) {
			// Add your code here 
	      int i=0;
	      int j=0;
	      int pl=0;
	      Node tmp;
	      WhatAStruct str3 = new WhatAStruct((this.node.length)/2); //KATASKEYAZETAI BOITHITIKO WhatAStruct POY PAIRNEI SAN ORISMA TO MISO TOY MEGETHOYS TOY PINAKA THS this 
																	//(ARA THA EXOYME PINAKA ME MEGETHOS TOYLAXITSON EKEINOY THS this)

	           
	      for(i=0;i<this.node.length; i++){
	        if (this.node[i]!=null && this.node[i]!=noNode){
	          pl++;												//BRISKETAI TO PLITHOS TWN STOIXEIWN STON PINAKA THS this
	          str3.insert(new Node(this.node[i].id, this.node[i].priority)); //EISAGWNTAI ANTIGRAFA OLWN TWN STOIXEIWN THS this STHN BOITHITIKH str3
	        }
	      }
	      
	      if(pl<k){k=pl;}	//SE PERIPTOSH POY TO k EINAI MEGALYTERO TOY PLITHOYS STOIXEIWN POY EXEI O PINAKAS THS this, H EPISTREFOMENH DOMH THA PERIEXEI OLA AYTA TA STOIXEIA
	      WhatAStruct str4 = new WhatAStruct(k);  
	      
	      for(j=0;j<k;j++){
	    	tmp = str3.remove(); //MESW THS remove(), BRISKEI TO STOIXEIO ME TO MEGALYTERO priority, TO APOMAKRYNEI APO THN str3 (BOITHITIKH) KAI BAZEI THN tmp NA DEIXNEI SE AYTO
	        str4.insert(new Node(tmp.id, tmp.priority)); //EISAGEI ANTIGRAFO THS tmp KATHE FORA STHN str4
	      } //H DIADIKASIA EPANALAMBANETAI KATHE FORA STHN ANANEOMENH str3 (XWRIS TO PROHGOYMENO MEGALYTERO STOIXEIO) k FORES
	     return str4; //EPISTREFEI THN ZHTOYMENH WhatAStruct
		}	
}  
