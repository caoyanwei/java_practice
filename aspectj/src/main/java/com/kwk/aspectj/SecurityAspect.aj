package com.kwk.aspectj;

public aspect SecurityAspect {
    private Authenticator authenticator = new Authenticator();

    pointcut secureAccess()
            : execution(* MessageCommunicator.deliver(..));

    pointcut setter()
            : set(private !final !static * Person.*);

    before(): secureAccess() {
        System.out.println("Checking and authenticating user");
        authenticator.authenticate();
    }

    before(): setter() {
        System.out.println("before update");
    }

}