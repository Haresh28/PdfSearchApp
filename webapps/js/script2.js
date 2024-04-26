	function searchInFile(){
		let searchWord=document.getElementById("search-tab").value;
		let searchJson=`{word:${searchWord}}`;
		sendDataToSearch(searchJson);
		
	}
	async function sendDataToSearch(searchJson){
		console.log(searchJson);
		const fetchResult = await fetch("http://localhost:8000/StrutsLearning/searchInFile",{
			method:"POST",
			body:searchJson
			
		});
		const result=await fetchResult.json();
		const displayMsg= document.querySelector('#searchResult');
		displayMsg.textContent=result.response;
	}/**
 * 
 */