import {Author} from "./Author";
import {BookStats} from "./BookStats";

export class Book {

  public id: number;
  public author: Author;
  public publishDate: Date;
  public bookStats: BookStats;

  public constructor() {
  }

}
