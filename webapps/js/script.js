 function userValidation(){
	//e.preventDefault();
		let userName=document.getElementById("userName").value;
		let password=document.getElementById("password").value;
		var data = `{user:${userName},password:${password}}`;
		//let val=JSON.parse(data);
		sendDataForValidation(data);
		
	}
	
	async function sendDataForValidation(data){
		console.log(data);
		const fetchResult = await fetch("http://localhost:8000/StrutsLearning/login",{
			method:"POST",
			body:data
			
		});
		console.log(fetchResult);
		const result = await fetchResult.json();
		
		console.log(result);
		
		if(result.message=="success"){
			console.log("in success");
			const errorMsg=document.querySelector("#error-msg");
			errorMsg.textContent='';
			window.location.href="http://localhost:8000/StrutsLearning/success";
		}
		else{
			console.log("in error");
			const errorMsg=document.querySelector("#error-msg");
			errorMsg.textContent='Login Failed!';
		}
			
		
	}
	
	