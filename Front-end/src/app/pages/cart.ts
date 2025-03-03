export class Cart {

   plantid : number = 0;
   plantname : string = "";
   qty : number = 0;
   rate : number =0 ;
   cid : number = 0;
   cartId : number = 0;

   cartobj : Cart[] = [];

   setCart(cart: Cart[]) {
      this.cartobj = cart;
    }
  
    getCart(): Cart[] {
      return this.cartobj;
    }

}
