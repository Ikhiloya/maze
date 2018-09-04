package com.loya.maze.firebaseUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.loya.maze.resource.vm.LoginVM;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    //decode user token
    // idToken comes from the client app (shown above)
//    public LoginVM decodeToken(String idToken) {
//        FirebaseToken decodedToken = null;
//        try {
//            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//        }
//        assert decodedToken != null;
//        String uid = decodedToken.getUid();
//        String email = decodedToken.getEmail();
//        String name = decodedToken.getName();
//        String imageUrl = decodedToken.getPicture();
//
//        decodedToken
//        Map<String, Object> claims = decodedToken.getClaims();
//////    String   role = claims.values();
////        List<Object> roles = new ArrayList<>(claims.values());
////
////        if (Boolean.TRUE.equals(claims.get("admin"))) {
////            // Allow access to requested admin resource.
////        }
//
//
//
//        //decode the token, if true extract details and create a user record in the db and set custom claim
//
//
//    }

    public static boolean isTokenValid(String idToken) {
        boolean isValid = false;
        try {
            // Verify the ID token while checking if the token is revoked by passing checkRevoked
            // as true.
            boolean checkRevoked = true;
            FirebaseToken decodedToken = FirebaseAuth.getInstance()
                    .verifyIdToken(idToken, checkRevoked);
            // Token is valid and not revoked.
            isValid = true;
            String uid = decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            if (e.getErrorCode().equals("id-token-revoked")) {
                // Token has been revoked. Inform the user to re-authenticate or signOut() the user.
            } else {
                // Token is invalid.
            }
        }
        return isValid;
    }


//    //create a user record with the valid token
//    public static UserRecord createUser(User user) {
//
//        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                .setEmail(user.getEmail())
//                .setDisplayName(user.getUsername())
//                .setEmailVerified(false)
//                .setPassword(user.getPassword());
//
//
//
//        UserRecord userRecord = null;
//        try {
//            userRecord = FirebaseAuth.getInstance().createUser(request);
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Successfully created new user: " + userRecord.getUid());
//
//        return userRecord;
//        // return setUserRole(userRecord, "admin");
//        // Set admin privilege on the user corresponding to uid.
//
//        // The new custom claims will propagate to the user's ID token the
//        // next time a new one is issued.
//
//
//    }

    public static UserRecord setUserRole(UserRecord userRecord, String claim) {
        //append role to the new user
        String uid = userRecord.getUid();

        Map<String, Object> claims = new HashMap<>();
        claims.put(claim, true);
        try {
            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        return userRecord;
    }

//    public static UserRecord updateUser(User userRequest) {
//        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(userRequest.getUuid())
//                .setEmail(userRequest.getEmail())
//                .setDisplayName(userRequest.getUsername())
//                .setEmailVerified(false)
//                .setPassword(userRequest.getPassword());
//
//        UserRecord userRecord = null;
//        try {
//            userRecord = FirebaseAuth.getInstance().updateUser(request);
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//        }
//        assert userRecord != null;
//        System.out.println("Successfully updated user: " + userRecord.getUid());
//        return userRecord;
//
//    }

    public static boolean deleteUser(String uid) {
        boolean isDeleted = false;
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
            isDeleted = true;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully deleted user.");
        return isDeleted;
    }

//    public static UserRecord setUserRole( String uuid){
//        //append role to the new user
////        String uid = userRecord.getUid();
//
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(uuid, true);
//        try {
//            FirebaseAuth.getInstance().setCustomUserClaims(uuid, claims);
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//        }
//        return userRecord;
//    }

    //Use firebase Admmin SDK to manage user
    //create, update, delete, users
    //expose all methods as a REST service

}
