
package prisonmanagement.func;

import prisonmanagement.entity.User;

public class UserFunc {
    public boolean checkUser(User user) {
        if (user != null) {
            if ("1".equals(user.getUserName())
                    && "1".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
