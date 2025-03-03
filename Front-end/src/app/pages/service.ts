import { UsedItem } from "./used-item";

export class Service {
 
     servId : string = ""
     compId : string = ""
     maintainenanceDate = new Date();
     description : string = "";
     cost : any;
     usedItems : UsedItem[] = [];
}
