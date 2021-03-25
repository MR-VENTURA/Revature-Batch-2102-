import { Content } from "./content";
import { Account } from "./account";

export class Post {
    postId: number;
    authorId: Account;
    parentPostId: Post;
    flaggedForReview: boolean;
    likes: number;
    dislikes: number;
    lastActivityDate: any;
    contentId: Content;
}
