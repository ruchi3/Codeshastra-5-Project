import { ApiCallerService } from './../api-caller.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-individual-signup',
  providers: [ApiCallerService],
  templateUrl: './individual-signup.component.html',
  styleUrls: ['./individual-signup.component.css']
})
export class IndividualSignupComponent implements OnInit {

  postBody: any;
  fname: string;
  lname: string;
  contact: number;
  dob: string;
  age: number;
  email: string;
  pwd: string;
  confirmPwd: string;
  pan: string;
  accNo: string;
  bankName: string;
  riskFactor: number;
  budget: number;
  returnFactor: number;

  constructor(private apiCallerService: ApiCallerService, private router: Router) { }

  ngOnInit() {
  }

  signUp() {
    this.postBody = {
      "first_name": this.fname,
      "last_name": this.lname,
      "dob": this.dob,
      "age": this.age,
      "contact": this.contact,
      "email_id": this.email,
      "password": this.pwd,
      "confirm_password": this.confirmPwd,
      "pan_card": this.pan,
      "acc_no": this.accNo,
      "bank_name": this.bankName,
      "risk_factor": this.riskFactor,
      "target_return": this.returnFactor,
      "budget": this.budget
    }
    this.apiCallerService.makePostRequest("/user/individual_signup", this.postBody).subscribe(
      res => {
        if(res.error == null) {
          console.log("User Signed Up Successfully");
          this.router.navigate(['/profile',this.email]);
        } else {
          console.log(res.error);
        }
      }
    );
  }

}
