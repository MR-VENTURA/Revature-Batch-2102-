import {AccountRole} from './account-role';
import {AccountStatus} from './account-status';

export class Account {
    peopleId: number;
    username: string;
    userPass: string;
    accountStatuses: AccountStatus;
    accountRoles: AccountRole;
}