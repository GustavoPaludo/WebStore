import { ProblemList } from "../problem/problem-list.model";

export interface UserAuthenticationModel {
	jwtToken: string,
	problemList: ProblemList,
}
