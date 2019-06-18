import {UserDetails} from "./UserDetails";

export class User {

  public id: number;
  public username: string;
  public email: string;
  public password: string;
  public createdOn: Date;
  public modifiedOn: Date;
  public userDetails: UserDetails;
  public enabled: boolean;

  public constructor() {
  }

}
