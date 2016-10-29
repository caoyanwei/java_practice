package com.kwk.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Start {
    public static void main(String[] args) throws InterruptedException {
//        hello("aaaa", "bbb");
        justTest();
    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });
    }

    public static void justTest() throws InterruptedException {
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                System.out.println("call: " + s + " in thread " + Thread.currentThread().getName());
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                System.out.println("onComplete" + " in thread " + Thread.currentThread().getName());
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber){
                subscriber.onNext("Hello");
            }
        });

        Observable observableSequence = Observable.just("Hello", "Hi", "Aloha");

//        observableSequence.subscribe(onNextAction, onErrorAction, onCompletedAction);
        observableSequence.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                System.out.println("event in thread " + Thread.currentThread().getName());
                return s + s;
            }
        }).observeOn(Schedulers.immediate()).subscribeOn(Schedulers.io()).subscribe(onNextAction, onErrorAction, onCompletedAction);

        Thread.sleep(2000);
    }
}
