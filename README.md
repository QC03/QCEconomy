# QCEconomy

## Description
My Minecraft Economy Plugin


## API

### Money API
> *Control to User Money Data*

- int getUserMoney(String uuid)
  - uuid -> uuid
  - **Return to User's Money Data**

- void setUserMoney(String uuid, int money)
  - uuid -> uuid
  - money -> integer value
  - **Set User Money Data**


## Commands

### Admin Command
> Need OP Permission

- /돈관리 확인 (닉네임)
  - Check to User's Money

- /돈관리 설정 (닉네임) (값)
  - Set to User's Money

- /돈관리 지급 (닉네임) (값)
  - Add to User's Money

- /돈관리 차감 (닉네임) (값)
  - Subtract to User's Money


### User Command

- /돈
  - Check Your Money