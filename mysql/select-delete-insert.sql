delete from Base_Data;
delete from Failure;
delete from Event_Cause;
delete from Operator;
delete from User_Equipment;

select * from Base_Data;
select * from Failure;
select * from Event_Cause;
select * from Operator;
select * from User_Equipment;
select * from User;

INSERT INTO Base_Data (data_id, date_time, event_id, failure_id, ue_id, mcc, mnc, cell_id, duration, cause_code, ne_version, imsi, hier3_id, hier32_id, hier321_id) 
VALUES ('1','2013-01-11 17:15:00','4097', '30', '100100', '238','1','4','1000', '0', '11B', '344930000000011', '4.80953208161499E+018', '8.22689636094747E+018', '1.15044494090948E+018');

INSERT INTO User_Equipment (user_equipment_id, marketing_name, manufacturer, access_capability, model, vendor_name, ue_type, os, input_mode) 
VALUES ('100100','G410','Mitsubishi', 'GSM 1800, GSM 900', 'G410', 'Mitsubishi','test','test','test');

INSERT INTO User (user_name, password, user_type) VALUES ('Eoin','password','Administrator');

INSERT INTO Event_Cause (cause_code, event_id, description) VALUES ('0','4097','SUCCESS');

INSERT INTO Operator (mcc, mnc, country, operator_name) VALUES ('238','1','Denmark','TDC-DK');

INSERT INTO Failure (failure_id, description) VALUES ('0','EMERGENCY');

