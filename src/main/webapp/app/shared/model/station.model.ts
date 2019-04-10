import { ICompany } from 'app/shared/model/company.model';

export interface IStation {
    id?: string;
    name?: string;
    company?: ICompany;
}

export class Station implements IStation {
    constructor(public id?: string, public name?: string, public company?: ICompany) {}
}
