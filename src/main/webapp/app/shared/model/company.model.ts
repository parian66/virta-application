import { ICompany } from 'app/shared/model/company.model';

export interface ICompany {
    id?: string;
    name?: string;
    parent?: ICompany;
}

export class Company implements ICompany {
    constructor(public id?: string, public name?: string, public parent?: ICompany) {}
}
