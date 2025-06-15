import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"

        private const val TAG = "UserDatabaseHelper"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(name: String, email: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllUsers(): List<Pair<String, String>> {
        val userList = mutableListOf<Pair<String, String>>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            userList.add(name to email)
        }
        cursor.close()
        return userList
    }
}
